package tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends BaseTest {
    @Test(groups = {"withSuccessLogin","regression"})
    public void positiveLoginTest () {
//        open("/");
        loginPage.login(BASE_LOGIN, BASE_PASSWORD);
    }
}
