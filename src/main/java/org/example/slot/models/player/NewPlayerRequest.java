package org.example.slot.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Base64;

public record NewPlayerRequest(
        @JsonProperty("username") String username,
        @JsonProperty("password_change") String password,
        @JsonProperty("password_repeat") String rePassword,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name,
        @JsonProperty("surname") String surname,
        @JsonProperty("currency_code") String currencyCode
) {

    public static NewPlayerRequest randomValidPlayer() {
        var encoder = Base64.getEncoder();
        var pass = encoder.encodeToString(RandomStringUtils.randomAlphabetic(10).getBytes());
        return new NewPlayerRequest(
                RandomStringUtils.randomAlphabetic(10),
                pass, pass,
                RandomStringUtils.randomAlphanumeric(6) + "@example.com",
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                null // TODO: 6/4/22
        );
    }
}
