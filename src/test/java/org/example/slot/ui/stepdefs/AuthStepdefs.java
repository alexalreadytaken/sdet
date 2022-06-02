package org.example.slot.ui.stepdefs;

import io.cucumber.java.en.When;
import org.example.slot.pages.AuthPage;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthStepdefs {

    @Autowired
    private AuthPage authPage;

    @When("some")
    public void some(){
        System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
    }
}
