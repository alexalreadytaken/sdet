package org.example.slot.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponseMessage(
        @JsonProperty("name") String errorName,
        @JsonProperty("message") String message,
        @JsonProperty("code") Integer code,
        @JsonProperty("status") Integer statusCode
) {
}
