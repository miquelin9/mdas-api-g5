package com.ccm.user.user.domain;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) { super(message);}
}
