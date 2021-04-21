package com.ccm.user.user.application;

import com.ccm.user.user.domain.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

@QuarkusTest
public class AddFavouritePokemonUseCaseTest {
    @Inject
    AddFavouritePokemonUseCase addFavouritePokemonUseCase;

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    @Test
    public void shouldAddAFavouritePokemon() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        userRepository.create(new User("alberto", 1));
        FavouritePokemon pokemon = new FavouritePokemon(new FavouritePokemonId(123));
        addFavouritePokemonUseCase.addFavouritePokemonToUser(123, 1);

        User retrievedUser = userRepository.find(new UserId(1));
        FavouritePokemons pokemons = retrievedUser.getFavouritePokemons();

        FavouritePokemon retrievedPokemon = pokemons
            .getFavouritePokemonList()
            .stream()
            .filter(x -> x.getFavouritePokemonId().equals(new FavouritePokemonId(123))).findAny().get();
        Assertions.assertEquals(retrievedPokemon, pokemon);
    }
}
