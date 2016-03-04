package com.funnythingz.pokedexandroid.presentation.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.funnythingz.pokedexandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PokemonListViewHolder {

    @Bind(R.id.pokemon_list_pokemon_number)
    public TextView pokemonNumberTextView;

    @Bind(R.id.pokemon_list_pokemon_name)
    public TextView pokemonNameTextView;

    public PokemonListViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
