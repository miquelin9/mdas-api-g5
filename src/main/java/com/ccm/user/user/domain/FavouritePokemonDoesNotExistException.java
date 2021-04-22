package com.ccm.user.user.domain;

public class FavouritePokemonDoesNotExistException extends Exception {
    public FavouritePokemonDoesNotExistException(String message) {
        super(message);
    }
}
