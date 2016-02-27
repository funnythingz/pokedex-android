package com.funnythingz.pokedexandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.Bind;

public class AppActivity extends AppCompatActivity {

    @Bind(R.id.pokemon_list_view)
    ListView pokemonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
    }
}
