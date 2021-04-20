package com.ccm.pokemon.pokemonTypes.application;
import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemonTypes.domain.interfaces.PokemonTypeGetterInterface;
import com.ccm.pokemon.pokemonTypes.domain.interfaces.PokemonTypeParserInterface;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ApplicationScoped
public class GetPokemonTypeUseCase {

    @Inject
    @Named("Http")
    PokemonTypeGetterInterface pokemonTypeGetter;

    @Inject
    @Named("JSON")
    PokemonTypeParserInterface pokemonTypeParser;

    public String getPokemonTypeByPokemonName (String pokemonName) throws PokemonNotFoundException, TimeoutException, NetworkConnectionException, UnknownException {
        JSONObject pokemonJson = this.pokemonTypeGetter.getPokemonTypeJsonByPokemonName(pokemonName);
        List<PokemonType> pokemonTypeList = pokemonTypeParser.toPokemonTypeList(pokemonJson);
        return pokemonTypeList.toString();
    }
}
