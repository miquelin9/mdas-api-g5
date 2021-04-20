package com.ccm.pokemon.pokemonTypes.domain.interfaces;

import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.UnknownException;
import org.json.simple.JSONObject;

public interface PokemonTypeGetterInterface {

    public JSONObject getPokemonTypeJsonByPokemonName(String pokemonName) throws PokemonNotFoundException, TimeoutException, NetworkConnectionException, UnknownException;
}
