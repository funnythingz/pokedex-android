package domain;

import java.util.ArrayList;

import infra.PokemonAPI;
import infra.PokemonData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PokemonRepository {

    final String ENDPOINT = "http://pokeapi.co/api/v2/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Pokemon findByNumber(String pokemonNumber) {
        PokemonAPI pokemonAPI = retrofit.create(PokemonAPI.class);

        final ArrayList<Pokemon> pokemonList = new ArrayList<>();
        pokemonAPI.getPokemonData(pokemonNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PokemonData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(PokemonData pokemonData) {
                        pokemonList.add(new Pokemon(new PokemonId("1001111"),
                                new PokedexNumber(String.valueOf(pokemonData.getId())),
                                new PokemonName(pokemonData.getName()),
                                null));
                    }
                });

        Pokemon pokemon = pokemonList.get(0);
        return pokemon;

    }
}
