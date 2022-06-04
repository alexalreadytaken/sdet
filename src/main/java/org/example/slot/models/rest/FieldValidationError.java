package org.example.slot.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FieldValidationError(
        @JsonProperty("field") String field,
        @JsonProperty("message") String message
) {
}
