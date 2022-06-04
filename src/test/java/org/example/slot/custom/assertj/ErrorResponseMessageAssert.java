package org.example.slot.custom.assertj;

import org.assertj.core.api.ObjectAssert;
import org.example.slot.models.rest.ErrorResponseMessage;

public class ErrorResponseMessageAssert extends ObjectAssert<ErrorResponseMessage> {
    public ErrorResponseMessageAssert(ErrorResponseMessage msg) {
        super(msg);
    }

    public static ErrorResponseMessageAssert assertThat(ErrorResponseMessage msg) {
        return new ErrorResponseMessageAssert(msg);
    }

    public ErrorResponseMessageAssert isInvalidGrantType() {
        extracting(ErrorResponseMessage::message)
                .isEqualTo("The authorization grant type is not supported by the authorization server. Check the `grant_type` parameter.");
        return this;
    }

    public ErrorResponseMessageAssert isInvalidScope(String scope) {
        extracting(ErrorResponseMessage::message)
                .isEqualTo(String.format("The requested scope is invalid, unknown, or malformed Check the `%s` scope.", scope));
        return this;
    }

    public ErrorResponseMessageAssert isBadRequest() {
        extracting(ErrorResponseMessage::errorName).isEqualTo("Bad Request");
        extracting(ErrorResponseMessage::statusCode).isEqualTo(400);
        return this;
    }
}
