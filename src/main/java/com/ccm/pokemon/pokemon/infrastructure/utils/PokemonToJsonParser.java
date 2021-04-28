package com.ccm.pokemon.pokemon.infrastructure.utils;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonToJsonParser {
    public String parse(Pokemon pokemon) {
        JSONObject resultPokemon = new JSONObject();

        resultPokemon.put("id", pokemon.getPokemonId().getPokemonId());
        resultPokemon.put("name", pokemon.getName().getName());
        resultPokemon.put("types", pokemon.getPokemonTypes());
        
        return resultPokemon.toJSONString();
    }
}
