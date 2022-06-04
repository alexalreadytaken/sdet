package org.example.slot.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Player(
        @JsonProperty("id") Long id,
        @JsonProperty("country_id") Long countryId,
        @JsonProperty("timezone_id") Long timezoneId,
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("name") String name,
        @JsonProperty("surname") String surname,
        @JsonProperty("gender") String gender,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("birthdate") String birthdate,//string?
        @JsonProperty("bonuses_allowed") Boolean bonusAllowed,
        @JsonProperty("is_verified") Boolean isVerified
) {
}
