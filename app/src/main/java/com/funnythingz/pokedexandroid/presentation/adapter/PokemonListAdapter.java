package com.funnythingz.pokedexandroid.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.funnythingz.pokedexandroid.R;
import com.funnythingz.pokedexandroid.domain.Pokemon;
import com.funnythingz.pokedexandroid.presentation.PokemonActivity;
import com.funnythingz.pokedexandroid.presentation.adapter.holder.PokemonListViewHolder;

import java.util.List;

public class PokemonListAdapter extends ArrayAdapter<Pokemon> {

    public PokemonListAdapter(Context context, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PokemonListViewHolder holder;

        if (convertView != null) {
            holder = (PokemonListViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_pokemon_list, parent, false);
            holder = new PokemonListViewHolder(convertView);
            convertView.setTag(holder);
        }

        Pokemon pokemon = getItem(position);

        holder.pokemonNumberTextView.setText(pokemon.getPokedexNumber().getValue());
        holder.pokemonNameTextView.setText(pokemon.getName().getValue());
        holder.pokemonListPokemonLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PokemonActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("pokemonNumber", pokemon.getPokedexNumber().getValue());
            v.getContext().startActivity(intent);
        });

        if (pokemon.getNickname() != null) {
            holder.pokemonNameTextView.setText(pokemon.getNickname().getValue());
        }

        return convertView;
    }
}
