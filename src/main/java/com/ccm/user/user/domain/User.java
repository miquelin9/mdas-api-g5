package com.ccm.user.user.domain;

public class User {


    public User(String name, int userId) {
        this.name = new UserName(name);
        this.userId = new UserId(userId);
        this.favouritePokemons = new FavouritePokemons();
    }

    private UserName name;
    private UserId userId;
    private FavouritePokemons favouritePokemons;

    public UserId getUserId () {
        return this.userId;
    }

    public UserName getName() {
        return name;
    }

    public FavouritePokemons getFavouritePokemons() {
        return favouritePokemons;
    }

    public void addFavouritePokemon(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        this.favouritePokemons.addFavouritePokemonToList(pokemon);
    }

    public void removeFavouritePokemon(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        this.favouritePokemons.removeFavouritePokemonFromList(pokemon);
    }
}
