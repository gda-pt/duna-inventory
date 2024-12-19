package controllers;

import entities.UnitOfMeasure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.UnitOfMeasureService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/unit-of-measures")
@Produces("application/json")
@Consumes("application/json")
public class UnitOfMeasureController {

    @Inject
    UnitOfMeasureService unitOfMeasureService;

    @GET
    public List<UnitOfMeasure> getAllUnitsOfMeasure() {
        return unitOfMeasureService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getUnitOfMeasureById(@PathParam("id") Long id) {
        return unitOfMeasureService.findById(id)
                .map(unit -> Response.ok(unit).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        unitOfMeasureService.createUnitOfMeasure(unitOfMeasure);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        try {
            return Response.ok(unitOfMeasureService.updateUnitOfMeasure(unitOfMeasure)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUnitOfMeasure(@PathParam("id") Long id) {
        try {
            unitOfMeasureService.deleteUnitOfMeasure(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}