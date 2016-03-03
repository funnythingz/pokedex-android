package com.funnythingz.pokedexandroid.domain;

public class PokedexNumber {
    String value;

    public PokedexNumber(int number) {
        this(String.valueOf(number));
    }

    public PokedexNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
