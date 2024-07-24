package tests;

import models.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class ReportTests extends BaseTest {
    @Test(groups = "withSuccessLogin")
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
        reportPage.startDate();
        reportPage.endDate();
        reportPage.clickViewReport();
        Assert.assertTrue(reportPage.reportViewDisplayed());
        dashboardPage.clickDetailsWourkout();
        switchTo().window(1);
        wourkoutDetailsPage.isOpen();
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isOpen();
    }

    @Test(groups = "withSuccessLogin")
    public void negativeViewZoneReport() {
        dashboardPage.clickWorkoutReportPage();
        reportPage.isOpen();
        reportPage.clickZoneReport();
        reportPage.startDate();
        reportPage.endDate();
        reportPage.clickViewReport();
        Assert.assertTrue(reportPage.zoneWorkoutError(), "*Please select a valid Activity Type.");
    }
}
