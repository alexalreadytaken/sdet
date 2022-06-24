package org.example.slot.models.ui;

import com.fasterxml.jackson.annotation.JsonProperty;

public record User(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("password") String password
) {
}
