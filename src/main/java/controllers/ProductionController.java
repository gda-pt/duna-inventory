package controllers;

import entities.Production;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.ProductionService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/productions")
@Produces("application/json")
@Consumes("application/json")
public class ProductionController {

    @Inject
    ProductionService productionService;

    @GET
    public List<Production> getAllProductions() {
        return productionService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getProductionById(@PathParam("id") Long id) {
        return productionService.findById(id)
                .map(production -> Response.ok(production).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createProduction(Production production) {
        productionService.createProduction(production);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateProduction(Production production) {
        try {
            return Response.ok(productionService.updateProduction(production)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduction(@PathParam("id") Long id) {
        try {
            productionService.deleteProduction(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}