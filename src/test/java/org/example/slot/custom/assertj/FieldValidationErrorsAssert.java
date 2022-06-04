package org.example.slot.custom.assertj;

import org.assertj.core.api.ListAssert;
import org.example.slot.models.rest.FieldValidationError;

import java.util.List;

public class FieldValidationErrorsAssert extends ListAssert<FieldValidationError> {
    public FieldValidationErrorsAssert(List<? extends FieldValidationError> actual) {
        super(actual);
    }

    public static FieldValidationErrorsAssert assertThat(List<? extends FieldValidationError> actual) {
        return new FieldValidationErrorsAssert(actual);
    }

    public FieldValidationErrorsAssert containsUsernameTakenError(String username) {
        var err = new FieldValidationError("username",
                String.format("Username \"%s\" has already been taken.", username));
        as("username already taken error not found").contains(err);
        return this;
    }

    public FieldValidationErrorsAssert containsEmailTakenError(String email) {
        var err = new FieldValidationError("email",
                String.format("Email \"%s\" has already been taken.", email));
        as("email already taken error not found").contains(err);
        return this;
    }
}
