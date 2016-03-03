package com.funnythingz.pokedexandroid.ddd;

import java.io.Serializable;

public interface Identity<T> extends Serializable {
    T getValue();
}
