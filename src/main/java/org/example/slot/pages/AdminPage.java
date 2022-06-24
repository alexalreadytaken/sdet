package org.example.slot.pages;

import lombok.RequiredArgsConstructor;
import org.example.slot.services.UsersProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

@Service
@RequiredArgsConstructor
public class AdminPage {

    private final WebDriver driver;
    private final UsersProvider usersProvider;

    @FindBy(xpath = "//a[@data-target='#s-menu-users']")
    private WebElement usersMenu;

    @FindBy(xpath = "//a[@href='/user/player/admin']")
    private WebElement playersButton;

    @FindBy(xpath = "//li[contains(@class,'nav-profile')]//span")
    private WebElement headerUsername;

    @FindBy(xpath = "//div[@id='payment-system-transaction-grid']//tr[1]//td[3]")
    private WebElement firstPlayerId;

    @FindBy(xpath = "//div[@id='payment-system-transaction-grid']")
    private WebElement usersTable;

    private By columnByName(String column) {
        return By.xpath(String.format("//a[contains(@class,'sort-link') and text()='%s']", column));
    }

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    public void openPlayersPage() {
        usersMenu.click();
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(elementToBeClickable(playersButton));
        playersButton.click();
    }

    public void sortByColumn(String column) {
        driver.findElement(columnByName(column)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(attributeContains(usersTable, "class", "grid-view-loading"));
        new Actions(driver).pause(1000L).perform();
    }

    public void firstPlayerIdStartsWith(String str) {
        assert firstPlayerId.getText().startsWith(str);
    }

    public void usernameOnHeaderIs(String userId) {
        var user = usersProvider.getUserById(userId);
        assert headerUsername.getText().equals(user.name());
    }
}
