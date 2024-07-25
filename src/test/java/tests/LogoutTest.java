package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "withSuccessLogin")
public class LogoutTest extends BaseTest {
    public void positiveLogoutTest() {
        calendarPage.isOpen();
        dashboardPage.clickLogoutButton();
        Assert.assertTrue(logoutPage.successfullLogOutIsDisplayed());
    }
}
