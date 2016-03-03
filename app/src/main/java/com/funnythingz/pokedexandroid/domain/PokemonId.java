package com.funnythingz.pokedexandroid.domain;

import com.funnythingz.pokedexandroid.ddd.AbstractIdentity;

public class PokemonId extends AbstractIdentity<String> {

    public PokemonId(String value) {
        super(value);
    }
}
