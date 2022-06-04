package org.example.slot.beans;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.slot.pages.AdminPage;
import org.example.slot.pages.AuthPage;
import org.example.slot.services.RestApiService;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public WebDriver webDriver() {
        return WebDriverManager
                .chromedriver()
                .getWebDriver();
    }

    @Bean
    public AuthPage authPage(WebDriver webDriver) {
        return new AuthPage(webDriver);
    }

    @Bean
    public AdminPage adminPage(WebDriver webDriver) {
        return new AdminPage(webDriver);
    }

    @Bean
    public RestApiService restApiService() {
        return new RestApiService();
    }
}
