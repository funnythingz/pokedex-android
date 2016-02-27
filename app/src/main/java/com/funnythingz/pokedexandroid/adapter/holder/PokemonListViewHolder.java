package com.funnythingz.pokedexandroid.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.funnythingz.pokedexandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PokemonListViewHolder {

    @Bind(R.id.pokemon_list_pokemon_nickname)
    public TextView nicknameTextView;

    public PokemonListViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
