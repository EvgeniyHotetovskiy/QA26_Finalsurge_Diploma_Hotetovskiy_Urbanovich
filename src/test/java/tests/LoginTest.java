package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"withSuccessLogin", "regression", "smoke"})
    public void positiveLoginTest() {
        dashboardPage.isOpen();
    }
    @Test(groups = {"regression", "smoke"}, dataProvider = "Негативные тестовые данные для логина")
    public void negativeLoginTest(String email, String password, String errorMessage) {
        loginPage.isOpen();
        loginPage.login(email, password);
        loginPage.isDisplayEmailMessageError();
        Assert.assertEquals(loginPage.getErrorEmailMessageText(), errorMessage);
    }
    @DataProvider(name = "Негативные тестовые данные для логина")
    public Object[][] testDataForLoginTest() {
        String invalidEmailErrorText = "Please enter a valid email address.";
        String emptyEmailErrorText = "Please enter your e-mail address.";
        return new Object[][]{
                {"123dsa", "fsd", invalidEmailErrorText},
                {"", "123sdf", emptyEmailErrorText}
        };
    }
}
