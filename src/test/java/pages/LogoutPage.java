package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class LogoutPage extends BasePage {

    private static final By ACCOUNT_LOGIN_BUTTON = By.cssSelector(".signup");
    private static final By LOGOUT_MESSAGE = By.cssSelector(".alert.alert-success");

    @Override
    public void isOpen() {
        $(ACCOUNT_LOGIN_BUTTON).shouldBe(clickable);
    }

    public String successfulLogOutIsDisplayed() {
        return $(LOGOUT_MESSAGE).getText();
    }

}
