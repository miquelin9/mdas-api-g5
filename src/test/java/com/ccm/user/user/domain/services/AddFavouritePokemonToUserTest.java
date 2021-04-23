package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.vo.FavouritePokemonId;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class AddFavouritePokemonToUserTest {
    @Inject
    AddFavouritePokemonToUser addFavouritePokemonToUser;

    @Test
    public void shouldAddAFavouritePokemon() throws UserNotFoundException, FavouritePokemonAlreadyExistsException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);
        FavouritePokemonId favouritePokemonId = new FavouritePokemonId(123);

        UserFinder userFinder = Mockito.mock(UserFinder.class);
        UserSaver userSaver = Mockito.mock(UserSaver.class);
        Mockito.when(userFinder.findUser(userId)).thenReturn(user);
        Mockito.when(userSaver.saveUser(user));
        QuarkusMock.installMockForType(userFinder, UserFinder.class);
        QuarkusMock.installMockForType(userSaver, UserSaver.class);

        addFavouritePokemonToUser.execute(favouritePokemonId, userId);
    }
}
