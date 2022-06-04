package org.example.slot.models.rest.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthTokenRequest(
        @JsonProperty("grant_type") String grantType,
        @JsonProperty("scope") String scope,
        @JsonProperty("username") String username,
        @JsonProperty("password") String password
) {

    public static AuthTokenRequest guest() {
        return new AuthTokenRequest("client_credentials", "guest:default", null, null);
    }

    public static AuthTokenRequest loginPassword(String login, String password) {
        return new AuthTokenRequest("password", null, login, password);
    }
}
