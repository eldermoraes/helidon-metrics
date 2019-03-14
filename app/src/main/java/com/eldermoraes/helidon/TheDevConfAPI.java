package com.eldermoraes.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class TheDevConfAPI {

    private String auth = "Basic NDU2YzY0NjU3MjRkNmY3MjYxNjU3MzdjNjUzODMxNjEzNTMzMzAzNTY0NjMzODYzMzczOTMyNjYzNDMwMzU2NTYxMzc2NTMyOmFiN2FmNTIzYmIyODAzMjk2ZjgxMTMzZjFlMWNkNjk1OTFjMjBkNWFhMWEyMTU0YjEyOWM0YTE4ZDM5Mzk1ZTIyMzNkZjZkM2IxMDAwOTYxOTg0MTMxYzE5NTQ0";

    private String getToken() {
        Client client = null;
        String response;
        try{
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/oauth2/token");
            response = target.request()
                    .header("Authorization", auth)
                    .header("Content-Type", "application/json")
                    .get(String.class);
        } finally {
            if (client != null)
                client.close();
        }

        return response;
    }

    public Response getEventos() {
        Client client = null;
        Response response;
        try{
            client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.globalcode.com.br/v1/publico/eventos");
            response = target.request()
                    .header("Authorization", "Bearer " + getToken())
                    .header("Content-Type", "application/json")
                    .get();
        } finally {
            if (client != null)
                client.close();
        }

        return response;
    }
}
