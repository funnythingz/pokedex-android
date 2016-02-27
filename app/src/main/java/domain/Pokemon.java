package domain;

import ddd.AbstractEntity;

public class Pokemon extends AbstractEntity<PokemonId> {

    PokedexNumber pokedexNumber;
    PokemonName name;
    PokemonNickname nickname;

    public Pokemon(PokemonId identity,
                   PokedexNumber pokedexNumber,
                   PokemonName pokemonName,
                   PokemonNickname pokemonNickname) {
        super(identity);
        this.name = pokemonName;
        this.pokedexNumber = pokedexNumber;
        this.nickname = pokemonNickname;
    }

    public PokemonName getName() {
        return name;
    }

    public PokedexNumber getPokedexNumber() {
        return pokedexNumber;
    }

    public PokemonNickname getNickname() {
        return nickname;
    }
}
