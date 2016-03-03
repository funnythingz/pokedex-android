package com.funnythingz.pokedexandroid.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.funnythingz.pokedexandroid.R;
import com.funnythingz.pokedexandroid.presentation.adapter.PokemonListAdapter;
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

import static android.widget.Toast.LENGTH_SHORT;

public class AppActivity extends AppCompatActivity {

    private CompositeSubscription compositeSubscription;

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

        ProgressDialog progressDialog = DialogHelper.progressDialog(this, getString(R.string.pokemon_list_loading), false);
        progressDialog.show();

        Observable<List<Pokemon>> observable = PokemonRepository.getInstance().fetchPokemonList();
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
                Toast.makeText(getApplication(), getString(R.string.pokemon_list_loading_error), LENGTH_SHORT).show();
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
