package infra;

import com.google.gson.annotations.SerializedName;

public class PokemonData {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public PokemonData(int id, String name) {
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
