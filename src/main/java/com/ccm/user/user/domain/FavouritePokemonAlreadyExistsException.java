package com.ccm.user.user.domain;

public class FavouritePokemonAlreadyExistsException extends Exception {
    public FavouritePokemonAlreadyExistsException(String message) {
        super(message);
    }
}
