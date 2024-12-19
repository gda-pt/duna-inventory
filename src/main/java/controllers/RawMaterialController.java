package controllers;

import entities.RawMaterial;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.RawMaterialService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/raw-materials")
@Produces("application/json")
@Consumes("application/json")
public class RawMaterialController {

    @Inject
    RawMaterialService rawMaterialService;

    @GET
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getRawMaterialById(@PathParam("id") Long id) {
        return rawMaterialService.findById(id)
                .map(rawMaterial -> Response.ok(rawMaterial).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createRawMaterial(RawMaterial rawMaterial) {
        rawMaterialService.createRawMaterial(rawMaterial);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateRawMaterial(RawMaterial rawMaterial) {
        try {
            return Response.ok(rawMaterialService.updateRawMaterial(rawMaterial)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRawMaterial(@PathParam("id") Long id) {
        try {
            rawMaterialService.deleteRawMaterial(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}