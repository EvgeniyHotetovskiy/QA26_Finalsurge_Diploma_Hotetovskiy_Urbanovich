package pages;

import com.codeborne.selenide.Condition;
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

    public boolean successfullLogOutIsDisplayed() {
        $(LOGOUT_MESSAGE).shouldHave(Condition.text("You have been successfully logged out of the system."));
        return true;

    }

}
