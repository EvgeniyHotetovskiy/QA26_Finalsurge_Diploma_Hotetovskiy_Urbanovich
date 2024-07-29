package tests;

import models.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class ReportTests extends BaseTest {
    @Test(groups = {"withSuccessLogin", "regression", "smoke"})
    public void positiveViewWorkoutReport() throws InterruptedException {
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
        dashboardPage.clickDetailsWourkout();
        switchTo().window(1);
        wourkoutDetailsPage.isOpen();
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isOpen();
    }

    @Test(groups = {"withSuccessLogin",  "regression"})
    public void negativeViewZoneReport() {
        dashboardPage.clickWorkoutReportPage();
        reportPage.isOpen();
        reportPage.clickZoneReport();
        reportPage.setStartDate(-1);
        reportPage.setEndDate(1);
        reportPage.clickViewReport();
        Assert.assertTrue(reportPage.zoneWorkoutError(), "*Please select a valid Activity Type.");
    }
}
