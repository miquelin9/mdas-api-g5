package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.UserNotFoundException;
import com.ccm.user.user.domain.interfaces.UserRepository;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import com.ccm.user.user.infrastructure.InMemoryUserRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import java.rmi.UnexpectedException;

import static org.mockito.Mockito.mock;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
public class UserCreatorTest {
    @Inject
    UserCreator tested;

    @Test
    public void verify_createUser_callsToMethods() throws UserAlreadyExistsException, UserNotFoundException {
        User user = mock(User.class);

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        Mockito.doNothing().when(userRepository).create(user);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        tested.createUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).create(Mockito.any());
    }

    @Test()
    public void verify_createUser_throwsUserAlreadyExistsException_whenUserIsAlreadyCreated() throws UserAlreadyExistsException, UserNotFoundException {
        User user = mock(User.class);

        UserRepository userRepository = mock(InMemoryUserRepository.class);
        Mockito.doThrow(new UserAlreadyExistsException("BlaBla")).when(userRepository).create(user);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        tested.createUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).create(Mockito.any());
    }
}
