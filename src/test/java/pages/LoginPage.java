package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final  By inputEmailField = By.cssSelector("#login_name");
    private static final By inputPasswordField = By.cssSelector("#login_password");
    private static final By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmailValue (String email) {
        $(inputEmailField).setValue(email);
    }
    public void setPasswordValue (String password) {
        $(inputPasswordField).setValue(password);
    }
    public void clickLoginButton () {
        $(loginButton).shouldBe(visible).click();
    }
    @Step ("Авторизироваться с логином {email} и паролем {password}")
    public void login(String email, String password) {
        setEmailValue(email);
        setPasswordValue(password);
        clickLoginButton();
    }
}
