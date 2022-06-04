package org.example.slot.rest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.slot.services.RestApiService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RestApiService.class)
public abstract class RestTest {

    @Autowired
    protected RestApiService apiService;

    @BeforeAll
    public static void setRestAssuredLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
