package org.example.slot.rest;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ResponseOptions;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.slot.models.rest.ErrorResponseMessage;
import org.example.slot.models.rest.FieldValidationError;
import org.example.slot.models.rest.auth.AuthTokenRequest;
import org.example.slot.models.rest.player.NewPlayerRequest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.slot.custom.assertj.ErrorResponseMessageAssert.assertThat;
import static org.example.slot.custom.assertj.FieldValidationErrorsAssert.assertThat;

public class RestNegativeTest extends RestTest {

    @Test
    public void testGetGuestTokenInvalidGrantType() {
        var req = new AuthTokenRequest("grant?", AuthTokenRequest.guest().scope(), null, null);
        var resp = apiService.authorize(req);
        assertThat(resp)
                .isNotNull()
                .extracting(ResponseOptions::statusCode)
                .isEqualTo(400);
        var err = resp.body().as(ErrorResponseMessage.class, ObjectMapperType.JACKSON_2);
        assertThat(err)
                .isBadRequest()
                .isInvalidGrantType();
    }

    @Test
    public void testGetGuestTokenInvalidScope() {
        String scope = "who?";//only one word
        var req = new AuthTokenRequest(AuthTokenRequest.guest().grantType(), scope, null, null);
        var resp = apiService.authorize(req);
        assertThat(resp)
                .isNotNull()
                .extracting(ResponseOptions::statusCode)
                .isEqualTo(400);
        var err = resp.body().as(ErrorResponseMessage.class, ObjectMapperType.JACKSON_2);
        assertThat(err)
                .isBadRequest()
                .isInvalidScope(scope);
    }

    @Test
    public void testCreatePlayerWithDecodedPassword() {
        var token = getGuestToken();
        var req = new NewPlayerRequest(
                RandomStringUtils.randomAlphabetic(10),
                "password", "password",
                "some@example.com",
                "name", "surname", null
        );
        var resp = apiService.regNewPlayer(req, token);
        assertThat(resp)
                .isNotNull()
                .extracting(ResponseOptions::statusCode)
                .isEqualTo(422);
        // FIXME: 6/4/22 least 6 characters???
    }

    @Test
    public void testCreatePlayerWithExistedUsernameAndEmail() {
        var token = getGuestToken();
        var req = NewPlayerRequest.randomValidPlayer();
        apiService.regNewPlayer(req, token);
        var resp = apiService.regNewPlayer(req, token);
        assertThat(resp)
                .isNotNull()
                .extracting(ResponseOptions::statusCode)
                .isEqualTo(422);
        var errors = resp.body().as(FieldValidationError[].class, ObjectMapperType.JACKSON_2);
        assertThat(Arrays.stream(errors).toList())
                .containsEmailTakenError(req.email())
                .containsUsernameTakenError(req.username());
    }
}
