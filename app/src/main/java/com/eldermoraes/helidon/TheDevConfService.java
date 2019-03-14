package com.eldermoraes.helidon;

import org.eclipse.microprofile.metrics.annotation.Metered;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tdcservice")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TheDevConfService {

    @Inject
    private TheDevConfAPI tdcapi;

    @GET
    @Metered
    public Response eventos(){
        return tdcapi.getEventos();
    }
}
