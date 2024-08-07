package tests;

import models.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class ReportTests extends BaseTest {
    private static final String ZONE_WORKOUT_ERROR  = "×\n" + "Please fix the following errors:\n" + "*Please select a valid Activity Zone Type.";
    @Test(groups = {"withSuccessLogin", "regression", "smoke", "deleteWorkout"})
    public void positiveViewWorkoutReport() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Walk")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        dashboardPage.clickWorkoutReportPage();
        reportPage.isOpen();
        reportPage.setStartDate(-1);
        reportPage.setEndDate(1);
        reportPage.clickViewReport();
        Assert.assertTrue(reportPage.reportViewDisplayed());
        dashboardPage.clickDetailsWorkout();
        switchTo().window(1);
        workoutDetailsPage.isOpen();
    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void negativeViewZoneReport() {
        dashboardPage.clickWorkoutReportPage();
        reportPage.isOpen();
        reportPage.clickZoneReport();
        reportPage.setStartDate(-1);
        reportPage.setEndDate(1);
        reportPage.clickViewReport();
        Assert.assertEquals(reportPage.zoneWorkoutError(), ZONE_WORKOUT_ERROR);
    }
}
