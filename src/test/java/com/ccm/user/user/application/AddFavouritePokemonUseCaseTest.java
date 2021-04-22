package com.ccm.user.user.application;

import com.ccm.user.user.domain.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class AddFavouritePokemonUseCaseTest {
    @Inject
    AddFavouritePokemonUseCase addFavouritePokemonUseCase;

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    @Test
    public void shouldAddAFavouritePokemon() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
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

    @Test
    public void shouldThrowFavouritePokemonAlreadyExistsException() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemon pokemon = new FavouritePokemon(new FavouritePokemonId(123));
        addFavouritePokemonUseCase.addFavouritePokemonToUser(123, 1);
        Exception exception = assertThrows(FavouritePokemonAlreadyExistsException.class, () -> {
            addFavouritePokemonUseCase.addFavouritePokemonToUser(123, 1);
        });
    }

    @BeforeEach
    public void checkDeleteAndCreateUser() {
        User user = new User("alberto", 1);

        if (userRepository.exists(user.getUserId())) {
            userRepository.delete(user);
        }

        userRepository.create(user);
    }
}
