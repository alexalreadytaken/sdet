package org.example.slot.pages;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthPage {

    private final WebDriver driver;

    private final By loginBy = By.xpath("//input[@id='UserLogin_username']");
    private final By passwordBy = By.xpath("//input[@id='UserLogin_password']");
    private final By signInBy = By.xpath("//input[@type='submit' and @value='Sign in']");

    @Value("${admin.page.entrypoint}")
    private String loginPageUrl;

    public void open() {
        driver.get(loginPageUrl);
    }

    @SneakyThrows
    public void login(String login, String password) {
        driver.findElement(loginBy).sendKeys(login);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(signInBy).click();
    }
}
