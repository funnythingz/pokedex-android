package com.funnythingz.pokedexandroid.infra;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPokemonListResponseData {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public GetPokemonListResponseData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        Pattern pattern = Pattern.compile("pokemon\\/([0-9]+)\\/$");
        Matcher matcher = pattern.matcher(url);
        matcher.find();
        return matcher.group(1);
    }
}
