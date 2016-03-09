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

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    public GetPokemonResponseData(int id, String name, String url, int height, int weight) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.height = height;
        this.weight = weight;
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

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
