package com.ccm.user.user.domain;

import com.ccm.pokemon.pokemonTypes.domain.valueObjects.PokemonId;

import java.util.ArrayList;
import java.util.List;

public class User {

    public User(String name, int userId) {
        this.name = new UserName(name);
        this.userId = new UserId(userId);
        this.favouritePokemonList = new ArrayList<>();
    }

    private UserName name;
    private UserId userId;
    private List<FavouritePokemon> favouritePokemonList;

    public UserId getUserId () {
        return this.userId;
    }

    public UserName getName() {
        return name;
    }

    public void addFavouritePokemon(FavouritePokemonId pokemonId) {
        favouritePokemonList.add(new FavouritePokemon(pokemonId));
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", userId=" + userId +
                ", favouritePokemonList=" + favouritePokemonList +
                '}';
    }
}
