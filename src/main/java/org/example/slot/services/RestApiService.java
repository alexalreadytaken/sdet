package org.example.slot.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.example.slot.models.rest.auth.AuthTokenRequest;
import org.example.slot.models.rest.player.NewPlayerRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestApiService {

    @Value("${rest.entrypoint}")
    private String url;
    @Value("${get.token.path}")
    private String guestTokenPath;
    @Value("${guest.token.username}")
    private String username;
    @Value("${players.path}")
    private String playersPath;
    @Value("${player.path}")
    private String playerPath;

    public Response authorize(AuthTokenRequest req) {
        return RestAssured.given()
                .auth().preemptive()
                .basic(username, "")
                .baseUri(url)
                .basePath(guestTokenPath)
                .body(req)
                .contentType(ContentType.JSON)
                .post();
    }

    public Response regNewPlayer(NewPlayerRequest req, String guestToken) {
        return players(guestToken)
                .body(req)
                .contentType(ContentType.JSON)
                .post();
    }

    public Response getPlayersList(String token) {
        return players(token).get();
    }

    public Response getPlayerInfo(Long playerId, String token) {
        return player(token, playerId).get();
    }

    private RequestSpecification player(String token, Long playerId) {
        return authRequest(token)
                .baseUri(url)
                .basePath(playerPath)
                .pathParam("id", playerId);
    }

    private RequestSpecification players(String token) {
        return authRequest(token)
                .baseUri(url)
                .basePath(playersPath);
    }

    private RequestSpecification authRequest(String token) {
        return RestAssured.given()
                .auth().preemptive()
                .oauth2(token);
    }
}
