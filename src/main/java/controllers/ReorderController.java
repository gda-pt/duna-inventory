package controllers;

import entities.ReorderLevel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import jakarta.inject.Inject;
import services.ReorderService;

import java.util.List;

@Path("/api/reorder")
@Produces("application/json")
@Consumes("application/json")
public class ReorderController {

    @Inject
    ReorderService reorderLevelService;

    @GET
    public List<ReorderLevel> getAllReorderLevels() {
        return reorderLevelService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getReorderLevelById(@PathParam("id") Long id) {
        return reorderLevelService.findById(id)
                .map(reorderLevel -> Response.ok(reorderLevel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createReorderLevel(ReorderLevel reorderLevel) {
        reorderLevelService.createReorderLevel(reorderLevel);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateReorderLevel(ReorderLevel reorderLevel) {
        try {
            return Response.ok(reorderLevelService.updateReorderLevel(reorderLevel)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReorderLevel(@PathParam("id") Long id) {
        try {
            reorderLevelService.deleteReorderLevel(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}