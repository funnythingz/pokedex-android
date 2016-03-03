package com.funnythingz.pokedexandroid.infra;

import com.google.gson.annotations.SerializedName;

public class GetPokemonResponseData {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public GetPokemonResponseData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
