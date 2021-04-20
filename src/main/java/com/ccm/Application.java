package com.ccm;
import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.valueObjects.Name;
import com.ccm.pokemon.pokemonTypes.infrastructure.parser.JsonPokemonTypeParser;
import com.ccm.pokemon.pokemonTypes.infrastructure.client.HTTPApiPokemonTypeRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@QuarkusMain
public class Application implements QuarkusApplication {

    @Override
    public int run (String... args) {

        String pokemonName = "";
        HTTPApiPokemonTypeRepository pokemonTypeGetterClient = new HTTPApiPokemonTypeRepository();
        JsonPokemonTypeParser pokemonTypeParser = new JsonPokemonTypeParser();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        System.out.println("Type a Pokemon name:");
        try {
            pokemonName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!pokemonName.equals("exit")) {
            try {
                JSONObject pokemonJson = pokemonTypeGetterClient.find(new Name(pokemonName));
                List<PokemonType> pokemonTypeList = pokemonTypeParser.toPokemonTypeList(pokemonJson);
                System.out.println(pokemonTypeList.toString());
            } catch (PokemonNotFoundException | TimeoutException | NetworkConnectionException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error. " + e.getMessage());
            }

            System.out.println("Type a Pokemon name:");
            try {
                pokemonName = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Exited succesfully");

        return 1;
    }
}