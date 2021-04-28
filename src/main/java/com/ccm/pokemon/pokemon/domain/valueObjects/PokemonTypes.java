package com.ccm.pokemon.pokemon.domain.valueObjects;

import java.util.ArrayList;
import java.util.List;

public class PokemonTypes {
    private List<PokemonType> pokemonTypes;

    public PokemonTypes() {
        this.pokemonTypes = new ArrayList<>();
    }

    public void addType(PokemonType pokemonType) {
        pokemonTypes.add(pokemonType);
    }
}
