package com.funnythingz.pokedexandroid.infra;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPokemonResponseData {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public GetPokemonResponseData(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        if (url != null) {
            Pattern pattern = Pattern.compile("pokemon\\/([0-9]+)\\/$");
            Matcher matcher = pattern.matcher(url);
            matcher.find();
            return matcher.group(1);
        }

        return String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
