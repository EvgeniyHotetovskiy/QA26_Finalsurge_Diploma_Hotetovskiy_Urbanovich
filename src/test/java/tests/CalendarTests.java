package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTests  extends BaseTest {

    @Test(groups = {"withSuccessLogin"})
    public void positiveAddQuickWorkOutByButton()  {
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.activityTypeQuickSelect("Run");
        calendarPage.clickAddWorkout();
        Assert.assertTrue(calendarPage.WorkOutIsDisplayed());
    }
}
