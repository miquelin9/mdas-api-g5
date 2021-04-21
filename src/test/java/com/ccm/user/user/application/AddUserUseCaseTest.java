package com.ccm.user.user.application;

import com.ccm.user.user.domain.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

@QuarkusTest
public class AddUserUseCaseTest {
    @Inject
    AddUserUseCase addUserUseCase;

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    @Test
    public void shouldCreatAUser() throws UserAlreadyExistsException {
        UserDTO user = new UserDTO("alberto",1);

        addUserUseCase.createUser(user);

        User retrievedUser = userRepository.find(new UserId(1));
        Assertions.assertTrue(userRepository.exists(new UserId(user.getUserId())));
        Assertions.assertEquals(new UserId(user.getUserId()), retrievedUser.getUserId());
        Assertions.assertEquals(new UserName(user.getName()), retrievedUser.getName());
    }
}
