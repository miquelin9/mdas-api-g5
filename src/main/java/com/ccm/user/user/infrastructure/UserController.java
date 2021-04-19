package com.ccm.user.user.infrastructure;

import com.ccm.user.user.application.AddFavouritePokemonUseCase;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Model
@Path("/user")
public class UserController {

    @Inject
    AddFavouritePokemonUseCase addFavouritePokemonUseCase;

    @GET
    @Path("/addFavouritePokemon")
    public String getTypes(@HeaderParam ("id") int userId, @QueryParam("id") int pokemonId) {
        try {
            return addFavouritePokemonUseCase.addFavouritePokemonToUser(pokemonId, userId);
        } catch (Exception e) {
            return "Unexpected error. " + e.getMessage();
        }
    }
}
