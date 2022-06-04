package org.example.slot.ui.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.slot.pages.AuthPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthStepdefs {

    @Autowired
    private AuthPage authPage;

    @Given("user open login page")
    public void userOpenLoginPage() {
        authPage.open();
    }

    @Then("user login {string} and password {string}")
    public void userLoginAndPassword(String login, String password) {
        authPage.login(login,password);
    }
}
