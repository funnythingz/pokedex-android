package com.funnythingz.pokedexandroid.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.funnythingz.pokedexandroid.R;
import com.funnythingz.pokedexandroid.domain.Pokemon;
import com.funnythingz.pokedexandroid.domain.PokemonRepository;
import com.funnythingz.pokedexandroid.helper.DialogHelper;
import com.funnythingz.pokedexandroid.helper.RxBusProvider;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static android.widget.Toast.LENGTH_SHORT;

public class PokemonActivity extends AppCompatActivity {

    private CompositeSubscription compositeSubscription;

    private String pokemonNumber;

    @Bind(R.id.pokemon_id_text_view)
    TextView pokemonIdTextView;

    @Bind(R.id.pokemon_name_text_view)
    TextView pokemonNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_pokemon);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        pokemonNumber = intent.getStringExtra("pokemonNumber");

        fetchPokemonView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(RxBusProvider.getInstance()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pokemon -> {
                    if (pokemon instanceof Pokemon) {
                        Pokemon p = (Pokemon) pokemon;
                        setTitle(p.getName().getValue());
                        pokemonIdTextView.setText(p.getPokedexNumber().getValue());
                        pokemonNameTextView.setText(p.getName().getValue());
                    }
                })
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void fetchPokemonView() {

        DialogHelper progressDialog = DialogHelper.newInstance(null, getString(R.string.pokemon_list_loading), false);
        progressDialog.show(getFragmentManager(), "progress");

        Observable<Pokemon> observable = PokemonRepository.getInstance().fetchPokemon(pokemonNumber);
        observable.subscribe(new Observer<Pokemon>() {
            @Override
            public void onCompleted() {
                Log.d("Completed", "");
                progressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error: ", "", e);
                progressDialog.dismiss();
                Toast.makeText(getApplication(), getString(R.string.pokemon_list_loading_error), LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Pokemon pokemon) {
                RxBusProvider.getInstance().send(pokemon);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
