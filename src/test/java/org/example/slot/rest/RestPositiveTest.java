package org.example.slot.rest;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ResponseOptions;
import org.example.slot.models.rest.auth.AuthTokenRequest;
import org.example.slot.models.rest.auth.AuthTokenResponse;
import org.example.slot.models.rest.player.NewPlayerRequest;
import org.example.slot.models.rest.player.Player;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.example.slot.custom.assertj.AuthTokenResponseAssert.assertThat;
import static org.example.slot.custom.assertj.NewPlayerResponseAssert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestPositiveTest extends RestTest {

    private String guestToken;
    private NewPlayerRequest newPlayerRequest;
    private Player createdPlayer;
    private String createdPlayerToken;

    @Test
    @Order(1)
    public void testGetGuestToken() {
        guestToken = getGuestToken();
    }

    @Test
    @Order(2)
    public void testRegisterNewPlayer() {
        assertThat(guestToken).as("guest token is null").isNotNull();
        var req = NewPlayerRequest.randomValidPlayer();
        var resp = apiService.regNewPlayer(req, guestToken);
        assertThat(resp).isNotNull().extracting(ResponseOptions::statusCode).isEqualTo(201);
        var newPlayerResp = resp.body().as(Player.class, ObjectMapperType.JACKSON_2);
        assertThat(newPlayerResp).playerCreatedValid().isEqualToSendedRequest(req);
        newPlayerRequest = req;
        createdPlayer = newPlayerResp;
    }

    @Test
    @Order(3)
    public void testAuthRegisteredPlayer() {
        assertPlayerCreated();
        var req = AuthTokenRequest
                .loginPassword(newPlayerRequest.username(), newPlayerRequest.password());
        var resp = apiService.authorize(req);
        assertThat(resp).isNotNull().extracting(ResponseOptions::statusCode).isEqualTo(200);
        var token = resp.body().as(AuthTokenResponse.class, ObjectMapperType.JACKSON_2);
        assertThat(token).isValidToken();
        createdPlayerToken = token.accessToken();
    }

    @Test
    public void testGetAllPlayers() {
        assertPlayerCreated();
        var resp = apiService.getPlayersList(createdPlayerToken);
        assertThat(resp).isNotNull().extracting(ResponseOptions::statusCode).isEqualTo(200);
        var players = resp.body().as(Player[].class, ObjectMapperType.JACKSON_2);
        assertThat(players).containsOnly(createdPlayer);
    }

    @Test
    public void testGetRegisteredPlayerInfo() {
        assertPlayerCreated();
        var resp = apiService.getPlayerInfo(createdPlayer.id(), createdPlayerToken);
        assertThat(resp).isNotNull().extracting(ResponseOptions::statusCode).isEqualTo(200);
        var player = resp.body().as(Player.class, ObjectMapperType.JACKSON_2);
        assertThat(player).isEqualTo(createdPlayer);
    }

    @Test
    public void testGetAnotherPlayerInfo() {
        assertPlayerCreated();
        var resp = apiService.getPlayerInfo(1L, createdPlayerToken);
        assertThat(resp).isNotNull().extracting(ResponseOptions::statusCode).isEqualTo(404);
        assertThatExceptionOfType(UnrecognizedPropertyException.class)
                .isThrownBy(() -> resp.body().as(Player.class, ObjectMapperType.JACKSON_2));
    }

    private void assertPlayerCreated() {
        assertThat(newPlayerRequest).as("new player request is null").isNotNull();
        assertThat(createdPlayer).as("player not created").isNotNull();
    }
}
