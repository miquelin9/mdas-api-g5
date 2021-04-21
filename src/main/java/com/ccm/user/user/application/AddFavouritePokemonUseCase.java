package com.ccm.user.user.application;

import com.ccm.user.user.domain.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddFavouritePokemonUseCase {

    @Inject
    UserFinder userFinder;

    @Inject
    UserSaver userSaver;

    public void addFavouritePokemonToUser (int pokemonId, int userId) throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemon _pokemonId = new FavouritePokemon(new FavouritePokemonId(pokemonId));
        UserId _userId = new UserId(userId);

        User user = userFinder.findUser(_userId);
        user.addFavouritePokemon(_pokemonId);
        userSaver.saveUser(user);
    }
}
