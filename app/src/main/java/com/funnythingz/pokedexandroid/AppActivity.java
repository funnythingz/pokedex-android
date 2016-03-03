package com.funnythingz.pokedexandroid;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.funnythingz.pokedexandroid.adapter.PokemonListAdapter;
import com.funnythingz.pokedexandroid.domain.Pokemon;
import com.funnythingz.pokedexandroid.domain.PokemonRepository;
import com.funnythingz.pokedexandroid.helper.DialogHelper;
import com.funnythingz.pokedexandroid.helper.RxBusProvider;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class AppActivity extends AppCompatActivity {

    private CompositeSubscription compositeSubscription;
    private PokemonRepository pokemonRepository;

    @Bind(R.id.pokemon_list_view)
    ListView pokemonListView;

    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        fetchPokemonListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(RxBusProvider.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemons -> {
                    if (pokemons instanceof List<?>) {
                        pokemonListView.setAdapter(new PokemonListAdapter(getApplicationContext(), R.layout.adapter_pokemon_list, (List<Pokemon>) pokemons));
                    }
                })
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void fetchPokemonListView() {

        ProgressDialog progressDialog = DialogHelper.progressDialog(this, "しゅとくちゅう", false);
        progressDialog.show();

        pokemonRepository = new PokemonRepository();
        Observable<List<Pokemon>> observable = pokemonRepository.fetchPokemonList();
        observable.subscribe(new Observer<List<Pokemon>>() {
            @Override
            public void onCompleted() {
                Log.d("Completed", "");
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error: ", "", e);
                progressDialog.dismiss();
            }

            @Override
            public void onNext(List<Pokemon> pokemons) {
                RxBusProvider.getInstance().send(pokemons);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                    fetchPokemonListView();
                }
            });
        }
    };
}
