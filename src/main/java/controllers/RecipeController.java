package controllers;

import entities.Recipe;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.RecipeService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/recipes")
@Produces("application/json")
@Consumes("application/json")
public class RecipeController {

    @Inject
    RecipeService recipeService;

    @GET
    public List<Recipe> getAllRecipes() {
        return recipeService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getRecipeById(@PathParam("id") Long id) {
        return recipeService.findById(id)
                .map(recipe -> Response.ok(recipe).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createRecipe(Recipe recipe) {
        recipeService.createRecipe(recipe);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateRecipe(Recipe recipe) {
        try {
            return Response.ok(recipeService.updateRecipe(recipe)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRecipe(@PathParam("id") Long id) {
        try {
            recipeService.deleteRecipe(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}