package org.example.slot.custom.assertj;

import org.assertj.core.api.ObjectAssert;
import org.example.slot.models.rest.auth.AuthTokenResponse;

public class AuthTokenResponseAssert extends ObjectAssert<AuthTokenResponse> {

    public AuthTokenResponseAssert(AuthTokenResponse authTokenResponse) {
        super(authTokenResponse);
    }

    public static AuthTokenResponseAssert assertThat(AuthTokenResponse authTokenResponse) {
        return new AuthTokenResponseAssert(authTokenResponse);
    }

    public AuthTokenResponseAssert isValidToken() {
        isNotNull();
        extracting(AuthTokenResponse::accessToken).isNotNull().isNotEqualTo("");
//      extracting(AuthTokenResponse::refreshToken).isNotNull().isNotEqualTo("");
        extracting(AuthTokenResponse::tokenType).isNotNull().isEqualTo("Bearer");
        extracting(AuthTokenResponse::expiresIn).isNotNull().matches(num -> num >= 0);//?
        return this;
    }
}
