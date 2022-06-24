package org.example.slot.pages;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.slot.services.UsersProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AuthPage {

    private final WebDriver driver;
    private final UsersProvider usersProvider;

    @FindBy(xpath = "//input[@id='UserLogin_username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='UserLogin_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit' and @value='Sign in']")
    private WebElement signInInput;

    @Value("${admin.page.entrypoint}")
    private String loginPageUrl;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(loginPageUrl);
    }

    @SneakyThrows
    public void login(String id) {
        var user = usersProvider.getUserById(id);
        loginInput.sendKeys(user.name());
        passwordInput.sendKeys(user.password());
        signInInput.click();
    }
}
