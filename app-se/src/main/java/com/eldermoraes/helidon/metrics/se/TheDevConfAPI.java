package com.eldermoraes.helidon.metrics.se;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class TheDevConfAPI {

    private static final String AUTH = "Basic NDU2YzY0NjU3MjRkNmY3MjYxNjU3MzdjNjUzODMxNjEzNTMzMzAzNTY0NjMzODYzMzczOTMyNjYzNDMwMzU2NTYxMzc2NTMyOmFiN2FmNTIzYmIyODAzMjk2ZjgxMTMzZjFlMWNkNjk1OTFjMjBkNWFhMWEyMTU0YjEyOWM0YTE4ZDM5Mzk1ZTIyMzNkZjZkM2IxMDAwOTYxOTg0MTMxYzE5NTQ0";

    private Token getToken() {
        Client client = null;
        String response;
        Token token = null;
        try{
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/oauth2/token");
            response = target.request()
                    .header("Authorization", AUTH)
                    .header("Content-Type", "application/json")
                    .get(String.class);

            Jsonb jsonb = JsonbBuilder.create();
            token = jsonb.fromJson(response, Token.class);
        } finally {
            if (client != null)
                client.close();
        }

        return token;
    }

    public String getEventList() {
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

    public String getEvent(int eventId) {
        Token token = getToken();
        Client client = null;
        String response;

        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/publico/evento/" + eventId);
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
