package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"smoke", "regression", "withSuccessLogin"})
public class LogoutTest extends BaseTest {
    private static final String LOGOUT_MESSAGE = "You have been successfully logged out of the system.";
    public void positiveLogoutTest() {
        calendarPage.isOpen();
        dashboardPage.clickLogoutButton();
        Assert.assertEquals(logoutPage.getSuccessfulLogOutMessage(),LOGOUT_MESSAGE);
    }
}

