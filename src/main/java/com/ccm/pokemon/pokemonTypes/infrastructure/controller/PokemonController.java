package com.ccm.pokemon.pokemonTypes.infrastructure.controller;

import com.ccm.pokemon.pokemonTypes.application.GetPokemonTypeUseCase;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemonTypes.domain.exceptions.PokemonNotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Model
@Path("/pokemon")
public class PokemonController {

    @Inject
    GetPokemonTypeUseCase getPokemonTypeUseCase;

    @GET
    @Path("/types")
    public Response getTypes(@QueryParam("name") String name) {
        try {
            String s = getPokemonTypeUseCase.getPokemonTypeByPokemonName(name);

            return Response.status(200).entity(s).build();
        } catch (PokemonNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        } catch (TimeoutException e) {
            return Response.status(408).entity(e.getMessage()).build();
        } catch (NetworkConnectionException e) {
            return Response.status(503).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity("Unexpected error. " + e.getMessage()).build();
        }
    }
}
