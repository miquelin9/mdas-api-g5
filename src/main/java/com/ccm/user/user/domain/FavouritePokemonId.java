package com.ccm.user.user.domain;

import java.util.Objects;

public class FavouritePokemonId {

    public FavouritePokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    private int pokemonId;

    public int getPokemonId() {
        return pokemonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouritePokemonId that = (FavouritePokemonId) o;
        return pokemonId == that.pokemonId;
    }
}
