package com.ccm.pokemon.pokemon.domain.aggregate;

import com.ccm.pokemon.pokemon.domain.valueObjects.Name;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonType;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonTypes;
import org.json.simple.JSONObject;

public class Pokemon {

    public Pokemon(Name name, PokemonId pokemonId) {
        this.name = name;
        this.pokemonId = pokemonId;
        this.pokemonTypes = new PokemonTypes();
    }

    public Name getName() {
        return name;
    }

    public PokemonId getPokemonId() {
        return pokemonId;
    }

    public PokemonTypes getPokemonTypes() {
        return pokemonTypes;
    }

    private Name name;
    private PokemonId pokemonId;
    private PokemonTypes pokemonTypes;

    public void addPokemonType(PokemonType pokemonType) {
        this.pokemonTypes.addType(pokemonType);
    }
}
