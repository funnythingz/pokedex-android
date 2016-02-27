package com.funnythingz.pokedexandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.funnythingz.pokedexandroid.adapter.PokemonListAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import domain.PokedexNumber;
import domain.Pokemon;
import domain.PokemonId;
import domain.PokemonName;
import domain.PokemonNickname;

public class AppActivity extends AppCompatActivity {

    @Bind(R.id.pokemon_list_view)
    ListView pokemonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);

        // TODO: PokemonRepositoryからPokemonを受け取る

        // FIXME: Pokemonを生成する際はFactoryメソッドパターンを使うこと
        // FIXME: とりあえずダミーとしてPokemonを適当に生成してViewに組み込む
        Pokemon pikachu = new Pokemon(new PokemonId("1001111"),
                new PokedexNumber("25"),
                new PokemonName("ピカチュウ"),
                null);
        Pokemon pikazo = new Pokemon(new PokemonId("1002222"),
                new PokedexNumber("25"),
                new PokemonName("ピカチュウ"),
                new PokemonNickname("ぴか蔵"));

        ArrayList<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(pikachu);
        pokemons.add(pikazo);

        PokemonListAdapter pokemonListAdapter = new PokemonListAdapter(getApplicationContext(), R.layout.adapter_pokemon_list, pokemons);
        pokemonListView.setAdapter(pokemonListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
