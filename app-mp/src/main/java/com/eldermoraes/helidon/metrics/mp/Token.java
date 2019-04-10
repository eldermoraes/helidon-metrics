package com.eldermoraes.helidon.metrics.mp;

import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;

public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonbProperty("Access-Token")
    private String accessToken;

    @JsonbProperty("token_type")
    private String tokenType;

    @JsonbProperty("expires_in")
    private int expires;

    public Token() {
    }

    public Token(String accessToken, String tokenType, int expires) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}
