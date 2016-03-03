package com.funnythingz.pokedexandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.funnythingz.pokedexandroid.adapter.PokemonListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import domain.Pokemon;
import domain.PokemonRepository;
import rx.Observable;
import rx.Observer;

public class AppActivity extends AppCompatActivity {

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

        PokemonRepository pokemonRepository = new PokemonRepository();
        Observable<List<Pokemon>> observable = pokemonRepository.fetchPokemonList();
        observable.subscribe(new Observer<List<Pokemon>>() {
            @Override
            public void onCompleted() {
                Log.d("Completed", "");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error: ", "", e);
            }

            @Override
            public void onNext(List<Pokemon> pokemons) {
                pokemonListView.setAdapter(new PokemonListAdapter(getApplicationContext(), R.layout.adapter_pokemon_list, pokemons));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // 3秒待機
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                }
            });
        }
    };
}
