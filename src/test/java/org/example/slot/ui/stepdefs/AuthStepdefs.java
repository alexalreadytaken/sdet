package org.example.slot.ui.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.slot.pages.AuthPage;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthStepdefs {

    @Autowired
    private AuthPage authPage;

    @Given("user open login page")
    public void userOpenLoginPage() {
        authPage.open();
    }

    @Then("user login by id {string}")
    public void userLoginById(String userId) {
        authPage.login(userId);
    }
}
