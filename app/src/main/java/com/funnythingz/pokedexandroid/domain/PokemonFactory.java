package com.funnythingz.pokedexandroid.domain;

import com.funnythingz.pokedexandroid.infra.GetPokemonResponseData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class PokemonFactory {

    public Pokemon createPokemon(GetPokemonResponseData getPokemonResponseData) {

        return new Pokemon(null,
                new PokedexNumber(getPokemonResponseData.getId()),
                new PokemonName(getPokemonResponseData.getName()),
                null);
    }

    public List<Pokemon> createPokemonList(List<GetPokemonResponseData> getPokemonResponseDataList) {

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        Observable.from(getPokemonResponseDataList)
                .map(this::createPokemon)
                .subscribe(pokemonList::add);

        return pokemonList;
    }
}
