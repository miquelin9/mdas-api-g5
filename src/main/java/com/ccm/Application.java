package com.ccm;
import com.ccm.pokemon.pokemonTypes.domain.aggregate.PokemonType;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemonTypes.infrastructure.parser.JsonPokemonTypeParser;
import com.ccm.pokemon.pokemonTypes.infrastructure.client.PokemonTypeGetterClient;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@QuarkusMain
public class Application {

    public static void main (String [] args) throws Exception {
        //EXECUTION OF QUARKUS NOT VERBOSE
        Quarkus.run(args);

        String pokemonName = "";
        PokemonTypeGetterClient pokemonTypeGetterClient = new PokemonTypeGetterClient();
        JsonPokemonTypeParser pokemonTypeParser = new JsonPokemonTypeParser();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        System.out.println("Type a Pokemon name:");
        pokemonName = reader.readLine();

        while (!pokemonName.equals("exit")) {
            try {
                JSONObject pokemonJson = pokemonTypeGetterClient.getPokemonTypeJsonByPokemonName(pokemonName);
                List<PokemonType> pokemonTypeList = pokemonTypeParser.toPokemonTypeList(pokemonJson);
                System.out.println(pokemonTypeList.toString());
            } catch (PokemonNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error. " + e.getMessage());
            }

            System.out.println("Dime el nombre de un Pokemon:");
            pokemonName = reader.readLine();
        }

        System.out.print("Exited succesfully");
    }
}