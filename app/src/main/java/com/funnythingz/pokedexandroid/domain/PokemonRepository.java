package com.funnythingz.pokedexandroid.domain;

import com.funnythingz.pokedexandroid.infra.PokemonAPI;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public class PokemonRepository {

    final String ENDPOINT = "http://pokeapi.co/api/v2/";

    private static final PokemonRepository pokemonRepository = new PokemonRepository();

    private PokemonAPI pokemonAPI;

    private PokemonRepository() {
        pokemonAPI =  new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(
                        new OkHttpClient()
                                .newBuilder()
                                .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(PokemonAPI.class);
    }

    public static PokemonRepository getInstance() {
        return pokemonRepository;
    }


    public Observable<List<Pokemon>> fetchPokemonList() {
        return pokemonAPI.getPokemonResponseDataList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(m -> PokemonFactory.createPokemonList(m.getPokemonResponseDataList()));
    }

    public Observable<Pokemon> fetchPokemon(String id) {
        return pokemonAPI.getPokemonResponseData(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(PokemonFactory::createPokemon);
    }
}
