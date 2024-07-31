package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final By INPUT_EMAIL_FIELD = By.cssSelector("#login_name");
    private static final By INPUT_PASSWORD_FIELD = By.cssSelector("#login_password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By ERROR_MESSAGE_EMAIL = By.cssSelector("label[class='error'][for='login_name']");

    @Override
    public void isOpen() {
        $(LOGIN_BUTTON).shouldBe(clickable);
    }

    public void setEmailValue(String email) {
        $(INPUT_EMAIL_FIELD).setValue(email);
    }

    public void setPasswordValue(String password) {
        $(INPUT_PASSWORD_FIELD).setValue(password);
    }

    public void clickLoginButton() {
        $(LOGIN_BUTTON).shouldBe(visible).click();
    }

    @Step("Авторизироваться с логином {email} и паролем {password}")
    public void login(String email, String password) {
        setEmailValue(email);
        setPasswordValue(password);
        clickLoginButton();
    }

    public String getErrorEmailMessageText() {
        return $(ERROR_MESSAGE_EMAIL).text();
    }

    public void isDisplayEmailMessageError() {
        $(ERROR_MESSAGE_EMAIL).shouldBe(visible);
    }
}
