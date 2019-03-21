/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eldermoraes.helidon.metrics.mp;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple JAX-RS resource to get TDCs events. Examples:
 *
 * Get all TDCs events:
 * curl -X GET http://localhost:8080/tdc
 *
 * Get details of some TDC event:
 * curl -X GET http://localhost:8080/tdc/eventId
 **
 * The message is returned as a JSON object.
 */
@Path("/tdc")
@RequestScoped
public class TheDevConfResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Inject
    private TheDevConfAPI theDevConfAPI;

    @Metered(name = "getEvents_metered")
    @Timed(name = "getEvents_timed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getEventList(){
        String response = theDevConfAPI.getEventList();

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }

    @Metered(name = "getEvent_metered")
    @Timed(name = "getEvent_timed")
    @Path("/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getEvent(@PathParam("eventId") int eventId){
        String response = theDevConfAPI.getEvent(eventId);

        return JSON.createObjectBuilder()
                .add("message", response)
                .build();
    }

}
