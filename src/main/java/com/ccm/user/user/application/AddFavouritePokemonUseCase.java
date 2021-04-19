package com.ccm.user.user.application;

import com.ccm.pokemon.pokemonTypes.domain.valueObjects.PokemonId;
import com.ccm.user.user.domain.*;

import javax.inject.Inject;

public class AddFavouritePokemonUseCase {

    @Inject
    UserFinder userFinder;

    @Inject
    UserSaver userSaver;

    public String addFavouritePokemonToUser (int pokemonId, int userId) throws UserNotFoundException {
        FavouritePokemonId _pokemonId = new FavouritePokemonId(pokemonId);
        UserId _userId = new UserId(userId);

        User user = userFinder.findUser(_userId);
        user.addFavouritePokemon(_pokemonId);
        return userSaver.saveUser(user).toString();

    }
}
