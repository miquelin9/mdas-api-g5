package com.ccm.user.user.infrastructure;

import com.ccm.user.user.application.AddFavouritePokemonUseCase;
import com.ccm.user.user.application.AddUserUseCase;
import com.ccm.user.user.application.UserDTO;
import com.ccm.user.user.domain.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.UserAlreadyExistsException;
import com.ccm.user.user.domain.UserNotFoundException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Model
@Path("/user")
public class UserController {

    @Inject
    AddFavouritePokemonUseCase addFavouritePokemonUseCase;

    @Inject
    AddUserUseCase addUserUseCase;

    @GET
    @Path("/addFavouritePokemon")
    public Response getTypes(@HeaderParam ("id") int userId, @QueryParam("id") int pokemonId) {
        try {
            addFavouritePokemonUseCase.addFavouritePokemonToUser(pokemonId, userId);
            return Response.status(200).build();
        } catch (FavouritePokemonAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        } catch (UserNotFoundException e) {
            return Response.status(403).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity("Unexpected error. " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/addUser")
    public Response addUser(@QueryParam("name") String name, @QueryParam("userId") int userId) {
        try {
            addUserUseCase.createUser(new UserDTO(name, userId));
            return Response.status(200).build();
        } catch (UserAlreadyExistsException e) {
            return Response.status(403).entity(e.getMessage()).build();
        }
    }
}
