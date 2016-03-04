package com.funnythingz.pokedexandroid.infra;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface PokemonAPI {

    @GET("pokemon")
    Observable<List<GetPokemonListResponseData>> getPokemonResponseDataList();

    @GET("pokemon/{id}")
    Observable<GetPokemonListResponseData> getPokemonResponseData(@Path("id") String id);
}
