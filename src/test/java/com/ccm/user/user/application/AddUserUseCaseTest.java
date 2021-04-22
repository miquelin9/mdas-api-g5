package com.ccm.user.user.application;

import com.ccm.user.user.domain.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AddUserUseCaseTest {
    @Inject
    AddUserUseCase addUserUseCase;

    @Inject
    @Named("InMemory")
    UserRepository userRepository;

    @Test
    public void shouldCreatAUser() throws UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO("alberto",1);
        User user = new User(userDTO.getName(), userDTO.getUserId());

        if (userRepository.exists(user.getUserId())) {
            userRepository.delete(user);
        }

        addUserUseCase.createUser(userDTO);

        User retrievedUser = userRepository.find(user.getUserId());
        assertTrue(userRepository.exists(user.getUserId()));
        assertEquals(user.getUserId(), retrievedUser.getUserId());
        assertEquals(user.getName(), retrievedUser.getName());
    }

    @Test
    public void shouldThrowUserAlreadyExistsException() throws UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO("alberto",1);
        User user = new User(userDTO.getName(), userDTO.getUserId());

        if (userRepository.exists(user.getUserId())) {
            userRepository.delete(user);
        }

        addUserUseCase.createUser(userDTO);

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            addUserUseCase.createUser(userDTO);
        });
    }
}
