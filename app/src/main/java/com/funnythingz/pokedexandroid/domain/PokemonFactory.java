package com.funnythingz.pokedexandroid.domain;

import com.funnythingz.pokedexandroid.infra.GetPokemonListResponseData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class PokemonFactory {

    public static Pokemon createPokemon(GetPokemonListResponseData getPokemonListResponseData) {

        return new Pokemon(null,
                new PokedexNumber(getPokemonListResponseData.getId()),
                new PokemonName(getPokemonListResponseData.getName()),
                null);
    }

    public static List<Pokemon> createPokemonList(List<GetPokemonListResponseData> getPokemonListResponseDataList) {

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        Observable.from(getPokemonListResponseDataList)
                .map(PokemonFactory::createPokemon)
                .subscribe(pokemonList::add);

        return pokemonList;
    }
}
