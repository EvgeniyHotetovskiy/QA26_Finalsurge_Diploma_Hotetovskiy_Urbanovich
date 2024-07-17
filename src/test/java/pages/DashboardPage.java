package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{
    private static final By DashboardButton = By.linkText("Default.cshtml");
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
}
