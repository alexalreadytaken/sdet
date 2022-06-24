package org.example.slot.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.slot.pages.AdminPage;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminStepdefs {

    @Autowired
    private AdminPage adminPage;

    @Then("user open players page")
    public void userOpenPlayersPage() {
        adminPage.openPlayersPage();
    }

    @When("user sort players by {string}")
    public void userSortPlayersBy(String column) {
        adminPage.sortByColumn(column);
    }

    @And("first user id starts with {string}")
    public void firstUserIdStartsWith(String str) {
        adminPage.firstPlayerIdStartsWith(str);
    }

    @And("user authorized by id {string}")
    public void userAuthorizedById(String userId) {
        adminPage.usernameOnHeaderIs(userId);
    }
}
