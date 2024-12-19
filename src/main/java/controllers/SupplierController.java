package controllers;

import entities.Supplier;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import services.SupplierService;

import jakarta.inject.Inject;
import java.util.List;

@Path("/api/suppliers")
@Produces("application/json")
@Consumes("application/json")
public class SupplierController {

    @Inject
    SupplierService supplierService;

    @GET
    public List<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getSupplierById(@PathParam("id") Long id) {
        return supplierService.findById(id)
                .map(supplier -> Response.ok(supplier).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createSupplier(Supplier supplier) {
        supplierService.createSupplier(supplier);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateSupplier(Supplier supplier) {
        try {
            return Response.ok(supplierService.updateSupplier(supplier)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSupplier(@PathParam("id") Long id) {
        try {
            supplierService.deleteSupplier(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}