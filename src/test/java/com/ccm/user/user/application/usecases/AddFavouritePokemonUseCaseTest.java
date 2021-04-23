package com.ccm.user.user.application.usecases;

import com.ccm.user.user.application.dto.UserFavouritePokemonDTO;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.services.AddFavouritePokemonToUser;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class AddFavouritePokemonUseCaseTest {
    @Inject
    AddFavouritePokemonUseCase addFavouritePokemonUseCase;

    @Test
    public void shouldAddFavouritePokemonToUser() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = new FavouritePokemonId(123);
        UserId userId = new UserId(1);
        UserFavouritePokemonDTO userFavouritePokemonDTO = new UserFavouritePokemonDTO(
            pokemonId.getPokemonId(),
            userId.getUserId()
        );

        AddFavouritePokemonToUser addFavouritePokemonToUser = Mockito.mock(AddFavouritePokemonToUser.class);
        Mockito.doNothing().when(addFavouritePokemonToUser).execute(pokemonId, userId);
        QuarkusMock.installMockForType(addFavouritePokemonToUser, AddFavouritePokemonToUser.class);

        addFavouritePokemonUseCase.addFavouritePokemon(userFavouritePokemonDTO);
    }
}
