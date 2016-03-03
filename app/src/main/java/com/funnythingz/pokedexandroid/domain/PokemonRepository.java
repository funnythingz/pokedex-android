package com.funnythingz.pokedexandroid.domain;

import com.funnythingz.pokedexandroid.infra.PokemonAPI;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PokemonRepository {

    final String ENDPOINT = "http://pokeapi.co/api/v2/";

    private static final PokemonRepository pokemonRepository = new PokemonRepository();

    private PokemonRepository() {
        // No instances.
    }

    public static PokemonRepository getInstance() {
        return pokemonRepository;
    }


    private PokemonAPI pokemonAPI = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(PokemonAPI.class);

    public Observable<List<Pokemon>> fetchPokemonList() {
        return pokemonAPI.getPokemonResponseDataList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(pokemonResponseData -> PokemonFactory.createPokemonList(pokemonResponseData));
    }
}
