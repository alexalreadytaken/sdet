package org.example.slot.pages;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPage {

    private final WebDriver driver;

    private final By usersBy = By.xpath("//a[@data-target='#s-menu-users']");
    private final By playersBy = By.xpath("//a[@href='/user/player/admin']");
    private final By firstPlayerIdBy =
            By.xpath("//div[@id='payment-system-transaction-grid']//tr[1]//td[3]");// FIXME: 6/4/22

    private By columnButtonBy(String column) {
        return By.xpath(String.format("//a[contains(@class,'sort-link') and text()='%s']", column));
    }

    public void openPlayersPage() {
        driver.findElement(usersBy).click();
        driver.findElement(playersBy).click();
    }

    @SneakyThrows
    public void sortByColumn(String column) {
        driver.findElement(columnButtonBy(column)).click();
        synchronized (driver){// FIXME: 6/4/22?
            driver.wait(2000);
        }
    }

    public void firstPlayerIdStartsWith(String str) {
        var id = driver.findElement(firstPlayerIdBy);
        assert id.getText().startsWith(str);
    }
}
