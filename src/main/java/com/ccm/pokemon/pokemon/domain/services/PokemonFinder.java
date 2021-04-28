package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PokemonFinder {
    @Inject
    @Named("Http")
    PokemonRepository pokemonRepository;

    public Pokemon findPokemon(PokemonId pokemonId) throws PokemonNotFoundException {
        return pokemonRepository.find(pokemonId);
    }
}
