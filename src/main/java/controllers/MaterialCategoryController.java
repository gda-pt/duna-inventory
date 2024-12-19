package controllers;

import entities.MaterialCategory;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.MaterialCategoryService;

@Path("/api/material-category")
@Produces("application/json")
@Consumes("application/json")
public class MaterialCategoryController {

    @Inject
    MaterialCategoryService rawMaterialCategoryService;

    @POST
    @Transactional
    public Response createMaterialCategory(MaterialCategory category, @HeaderParam("user") String currentUser) {
        MaterialCategory createdCategory = rawMaterialCategoryService.createMaterialCategory(category, currentUser);
        return Response.status(Response.Status.CREATED).entity(createdCategory).build();
    }

    @GET
    public Response getAllRawMaterialCategories() {
        return Response.ok(rawMaterialCategoryService.getAllRawMaterialCategories()).build();
    }

    @GET
    @Path("/{id}")
    public Response getMaterialCategoryById(@PathParam("id") Long id) {
        return rawMaterialCategoryService.getMaterialCategoryById(id)
                .map(category -> Response.ok(category).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMaterialCategory(@PathParam("id") Long id, MaterialCategory category, @HeaderParam("user") String currentUser) {
        try {
            MaterialCategory updatedCategory = rawMaterialCategoryService.updateMaterialCategory(id, category, currentUser);
            return Response.ok(updatedCategory).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMaterialCategory(@PathParam("id") Long id) {
        try {
            rawMaterialCategoryService.deleteMaterialCategory(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
