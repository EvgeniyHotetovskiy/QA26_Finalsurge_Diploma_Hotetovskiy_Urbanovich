import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final  By inputEmailField = By.cssSelector("#login_name");
    private static final By inputPasswordField = By.cssSelector("#login_password");
    private static final By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmailValue (String email) {
        driver.findElement(inputEmailField).sendKeys(email);
    }
    public void setPasswordValue (String password) {
        driver.findElement(inputPasswordField).sendKeys(password);
    }
    public void clickLoginButton () {
        driver.findElement(loginButton).click();
    }
    @Step ("Авторизироваться с логином {email} и паролем {password}")
    public void login(String email, String password) {
        setEmailValue(email);
        setPasswordValue(password);
        clickLoginButton();
    }

    @Step("Открыть сайт FinalSurge")
    public void open (String url) {
        driver.get(url);
    }
}
