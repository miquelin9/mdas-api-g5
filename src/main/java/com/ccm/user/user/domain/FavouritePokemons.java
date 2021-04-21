package com.ccm.user.user.domain;

import java.util.ArrayList;
import java.util.List;

public class FavouritePokemons {

    public FavouritePokemons() {
        favouritePokemonList = new ArrayList<>();
    }

    private final List<FavouritePokemon> favouritePokemonList;

    public void addFavouritePokemonToList(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        guard(pokemon);
        favouritePokemonList.add(pokemon);
    }

    public List<FavouritePokemon> getFavouritePokemonList() {
        return favouritePokemonList;
    }

    private void guard(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = pokemon.getFavouritePokemonId();

        if (favouritePokemonList.stream().anyMatch(favouritePokemon -> favouritePokemon.getFavouritePokemonId().equals(pokemonId))) {
            throw new FavouritePokemonAlreadyExistsException("The user already has the pokemon " + pokemonId.getPokemonId() + " as favourite");
        }
    }

}
