package com.eldermoraes.helidon.metrics.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class TheDevConfAPI {
    private static final String AUTH = "Basic NDU2YzY0NjU3MjRkNmY3MjYxNjU3MzdjNjUzODMxNjEzNTMzMzAzNTY0NjMzODYzMzczOTMyNjYzNDMwMzU2NTYxMzc2NTMyOmFiN2FmNTIzYmIyODAzMjk2ZjgxMTMzZjFlMWNkNjk1OTFjMjBkNWFhMWEyMTU0YjEyOWM0YTE4ZDM5Mzk1ZTIyMzNkZjZkM2IxMDAwOTYxOTg0MTMxYzE5NTQ0";

    private Token getToken() {
        Client client = null;
        Token response;
        try{
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/oauth2/token");
            response = target.request()
                    .header("Authorization", AUTH)
                    .header("Content-Type", "application/json")
                    .get(Token.class);
        } finally {
            if (client != null)
                client.close();
        }

        return response;
    }

    public String getEvents() {
        Token token = getToken();
        Client client = null;
        String response;

        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/publico/eventos");
            response = target.request()
                    .header("Authorization", token.getTokenType() + " " + token.getAccessToken())
                    .header("Content-Type", "application/json")
                    .get(String.class);
        } finally {
            if (client != null)
                client.close();
        }

        return response;
    }
}

