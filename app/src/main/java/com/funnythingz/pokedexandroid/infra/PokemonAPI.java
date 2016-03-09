package com.funnythingz.pokedexandroid.infra;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface PokemonAPI {

    @GET("pokemon")
    Observable<GetPokemonListResponseData> getPokemonResponseDataList();

    @GET("pokemon/{id}")
    Observable<GetPokemonResponseData> getPokemonResponseData(@Path("id") String id);
}
