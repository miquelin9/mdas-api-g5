package com.ccm.user.user.application.usecases;


import com.ccm.user.user.application.dto.UserDTO;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.services.UserCreator;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class AddUserUseCaseTest {
    @Inject
    AddUserUseCase addUserUseCase;

    @Test
    public void shouldAddAUser() throws UserAlreadyExistsException {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);
        UserDTO userDTO = new UserDTO("keko", 1);

        UserCreator userCreator = Mockito.mock(UserCreator.class);
        Mockito.doNothing().when(userCreator).createUser(user);
        QuarkusMock.installMockForType(userCreator, UserCreator.class);

        addUserUseCase.createUser(userDTO);
    }
}
