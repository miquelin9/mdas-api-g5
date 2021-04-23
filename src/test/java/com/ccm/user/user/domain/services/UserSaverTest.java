package com.ccm.user.user.domain.services;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.exceptions.UserAlreadyExistsException;
import com.ccm.user.user.domain.interfaces.UserRepository;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;
import com.ccm.user.user.infrastructure.InMemoryUserRepository;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

@QuarkusTest
public class UserSaverTest {
    @Inject
    UserSaver userSaver;

    @Test
    public void shouldUpdateUser() {
        UserId userId = new UserId(1);
        UserName userName = new UserName("keko");
        User user = new User(userName, userId);

        UserRepository userRepository = Mockito.mock(InMemoryUserRepository.class);
        Mockito.when(userRepository.update(user)).thenReturn(user);
        QuarkusMock.installMockForType(userRepository, UserRepository.class);

        userSaver.saveUser(user);
    }
}
