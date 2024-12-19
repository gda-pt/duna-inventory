package controllers;

import entities.Batch;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.BatchService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/batch")
@Produces("application/json")
@Consumes("application/json")
public class BatchController {

    @Inject
    BatchService batchService;

    @GET
    public List<Batch> getAllBatches() {
        return batchService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getBatchById(@PathParam("id") Long id) {
        return batchService.findById(id)
                .map(batch -> Response.ok(batch).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createBatch(Batch batch) {
        batchService.createBatch(batch);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateBatch(Batch batch) {
        try {
            return Response.ok(batchService.updateBatch(batch)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBatch(@PathParam("id") Long id) {
        try {
            batchService.deleteBatch(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}