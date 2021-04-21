package com.ccm.user.user.domain;

import com.ccm.pokemon.pokemonTypes.domain.valueObjects.PokemonId;

public class FavouritePokemon {

    public FavouritePokemon(FavouritePokemonId favouritePokemonId) {
        this.favouritePokemonId = favouritePokemonId;
    }

    private FavouritePokemonId favouritePokemonId;

    public FavouritePokemonId getFavouritePokemonId() {
        return favouritePokemonId;
    }
}
