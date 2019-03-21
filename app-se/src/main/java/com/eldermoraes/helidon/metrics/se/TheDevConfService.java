package com.eldermoraes.helidon.metrics.se;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.Collections;

public class TheDevConfService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Inject
    private TheDevConfAPI theDevConfAPI;

    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/tdc", this::getEventList)
                .get("/tdc/{eventId}", this::getEvent);

    }

    private void getEvent(ServerRequest serverRequest, ServerResponse serverResponse) {
        String eventId = serverRequest.path().param("eventId");
        sendResponse(serverResponse, theDevConfAPI.getEvent(Integer.parseInt(eventId)));
    }


    private void getEventList(ServerRequest serverRequest, ServerResponse serverResponse) {
        sendResponse(serverResponse, theDevConfAPI.getEventList());
    }

    private void sendResponse(ServerResponse response, String message) {
        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", message)
                .build();
        response.send(returnObject);
    }
}
