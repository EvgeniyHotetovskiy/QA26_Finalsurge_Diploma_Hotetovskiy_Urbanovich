package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = {"smoke", "regression", "withSuccessLogin"})
public class LogoutTest extends BaseTest {
    public void positiveLogoutTest() {
        calendarPage.isOpen();
        dashboardPage.clickLogoutButton();
        Assert.assertEquals(logoutPage.successfulLogOutIsDisplayed(), "You have been successfully logged out of the system.");
    }
}

