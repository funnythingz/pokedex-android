package domain;

import ddd.AbstractIdentity;

public class PokemonId extends AbstractIdentity<String> {

    protected PokemonId(String value) {
        super(value);
    }
}
