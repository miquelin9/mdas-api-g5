package com.ccm.user.user.application;

import com.ccm.user.user.domain.User;
import com.ccm.user.user.domain.UserAlreadyExistsException;
import com.ccm.user.user.domain.UserCreator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AddUserUseCase {
    @Inject
    UserCreator userCreator;

    public void createUser (UserDTO user) throws UserAlreadyExistsException {
        userCreator.createUser(new User(user.getName(), user.getUserId()));
    }
}
