package com.ccm.pokemon.pokemonTypes.domain.interfaces;

import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemonTypes.domain.valueObjects.Name;
import org.json.simple.JSONObject;

public interface PokemonTypeRepository {

    public JSONObject find(Name pokemonName) throws PokemonNotFoundException, TimeoutException, NetworkConnectionException, UnknownException;
}
