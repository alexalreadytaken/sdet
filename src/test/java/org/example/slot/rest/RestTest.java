package org.example.slot.rest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ResponseOptions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.example.slot.custom.assertj.AuthTokenResponseAssert;
import org.example.slot.models.rest.auth.AuthTokenRequest;
import org.example.slot.models.rest.auth.AuthTokenResponse;
import org.example.slot.services.RestApiService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.example.slot.custom.assertj.AuthTokenResponseAssert.assertThat;

@SpringBootTest(classes = RestApiService.class)
public abstract class RestTest {

    @Autowired
    protected RestApiService apiService;

    @BeforeAll
    public static void setRestAssuredLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    protected String getGuestToken() {
        var resp = apiService.authorize(AuthTokenRequest.guest());
        assertThat(resp)
                .isNotNull()
                .extracting(ResponseOptions::statusCode)
                .isEqualTo(200);
        var token = resp.body().as(AuthTokenResponse.class, ObjectMapperType.JACKSON_2);
        assertThat(token)
                .isValidToken();
        return token.accessToken();
    }
}
