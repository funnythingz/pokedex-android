package com.funnythingz.pokedexandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.funnythingz.pokedexandroid.R;
import com.funnythingz.pokedexandroid.adapter.holder.PokemonListViewHolder;

import java.util.List;

import domain.Pokemon;

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

        holder.nicknameTextView.setText(pokemon.getName().getValue());
        if (pokemon.getNickname() != null) {
            holder.nicknameTextView.setText(pokemon.getNickname().getValue());
        }
        return convertView;
    }
}
