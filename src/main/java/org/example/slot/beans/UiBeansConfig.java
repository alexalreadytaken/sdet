package org.example.slot.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.slot.pages.AdminPage;
import org.example.slot.pages.AuthPage;
import org.example.slot.services.UsersProvider;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class UiBeansConfig {

    // TODO: 6/24/22 dynamic
    @Bean
    public WebDriver driver() {
        var driver = WebDriverManager.chromiumdriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));
        return driver;
    }

    @Bean
    public AuthPage authPage(WebDriver driver, UsersProvider usersProvider) {
        return new AuthPage(driver, usersProvider);
    }

    @Bean
    public AdminPage adminPage(WebDriver driver, UsersProvider usersProvider) {
        return new AdminPage(driver, usersProvider);
    }

    @Bean
    public UsersProvider usersProvider() {
        return new UsersProvider();
    }
}
