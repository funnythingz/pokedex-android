package com.funnythingz.pokedexandroid.infra;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPokemonListResponseData {

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private List<GetPokemonResponseData> getPokemonResponseDataList;

    public GetPokemonListResponseData(int count, List<GetPokemonResponseData> getPokemonResponseDataList) {
        this.count = count;
        this.getPokemonResponseDataList = getPokemonResponseDataList;
    }

    public int getCount() {
        return count;
    }

    public List<GetPokemonResponseData> getPokemonResponseDataList() {
        return getPokemonResponseDataList;
    }
}
