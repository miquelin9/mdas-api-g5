package com.ccm.pokemon.pokemon.domain.valueObjects;

public class PokemonId {

    public PokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    private int pokemonId;

    public int getPokemonId() {
        return pokemonId;
    }
}
