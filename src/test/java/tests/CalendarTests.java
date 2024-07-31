package tests;

import models.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class CalendarTests extends BaseTest {
    @Test(groups = {"withSuccessLogin", "regression", "smoke", "deleteTodayWorkout"})
    public void positiveAddQuickWorkOutTodayByButton() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Run")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void negativeAddQuickWorkout() {
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.clickAddWorkout();
        Assert.assertEquals(calendarPage.activityTypeError(), "×\n" + "Please fix the following errors:\n" + "*Please select a valid Activity Type.");
    }

    @Test(groups = {"withSuccessLogin", "regression", "smoke", "deleteTodayWorkout"})
    public void addFullFromCalendar() {
        AddWorkout fullWorkout = new AddWorkout.AddWorkoutBuilder()
                .setName("morning run")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.clickFullWorkoutFromCalendar();
        addWorkoutPage.activityTypeSelect("run", "Long Run");
        addWorkoutPage.addWorkoutName(fullWorkout);
        addWorkoutPage.clickAddWorkout();
        Assert.assertEquals(addWorkoutPage.getWorkoutName(), fullWorkout.getName());
        dashboardPage.clickCalendar();
    }

    @Test(groups = {"withSuccessLogin", "regression", "deleteTodayWorkout"})
    public void editWorkout() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Swim")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutFromCalendar();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
        calendarPage.editTraining();
        workoutDetailsPage.isOpen();
        workoutDetailsPage.clickUpdateDWorkout();
        AddWorkout editWorkout = new AddWorkout.AddWorkoutBuilder()
                .setTimeOfDay("7:00 PM")
                .setName("workout edit test")
                .setDescription("evening freestyle swimming in the pool")
                .setShowPlannedDistance(true)
                .setpDistance("1")
                .setpDistanceType("km")
                .setpDuration("00:50:00")
                .setDistance("1.100")
                .setDistanceType("km")
                .setDuration("00:45:00")
                .setPaceType("min/km")
                .setPerEffort("4 (Moderate)")
                .setHowFeel("Good")
                .setkCal("300")
                .setSaveToLibrary(true)
                .build();
        workoutDetailsPage.editWorkout(editWorkout);
        workoutDetailsPage.clickSaveUpdateDWorkout();
        workoutDetailsPage.isOpen();
        dashboardPage.clickCalendar();
        Assert.assertTrue(calendarPage.workOutIsDisplayed());
    }

    @Test(groups = {"withSuccessLogin", "regression", "deleteWorkout"})
    public void viewFutureTrainingFromDashboardPage() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Bike")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.setWorkoutDate(1);
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        dashboardPage.clickDashboardPage();
        dashboardPage.clickUpcomingWorkouts();
        Assert.assertTrue(dashboardPage.upcomingWorkoutsExists());
        dashboardPage.clickDetailsWorkout();
        workoutDetailsPage.isOpen();
    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void viewPastTrainingFromDashboardPage() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Walk")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.setWorkoutDate(-1);
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        dashboardPage.clickDashboardPage();
        dashboardPage.clickPastWorkouts();
        Assert.assertTrue(dashboardPage.pastWorkoutsExists());
        dashboardPage.clickDetailsWorkout();
        workoutDetailsPage.isOpen();
        workoutDetailsPage.clickUpdateDWorkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isOpen();
        dashboardPage.clickDashboardPage();
        Assert.assertEquals(dashboardPage.pastWorkoutEmpty(), "You have no past workouts within the last 14 days.");
    }

    @Test(groups = {"regression", "smoke", "withSuccessLogin", "deleteWorkout"})
    public void fileUploadTest() {
        calendarPage.isOpen();
        calendarPage.clickPlusFromCalendar();
        calendarPage.clickUploadWorkout();
        calendarPage.uploadWorkout("src/test/resources/example.tcx", "Upload Workout");
        workoutDetailsPage.isOpen();
        calendarPage.downloadButtonIsClickable();
        Assert.assertEquals(workoutDetailsPage.EditWorkoutIsDisplayed(), "Upload Workout");
    }

    @Test(groups = {"regression", "smoke", "withSuccessLogin", "deleteWorkout"})
    public void fileDownloadTest() {
        calendarPage.isOpen();
        calendarPage.clickPlusFromCalendar();
        calendarPage.clickUploadWorkout();
        calendarPage.uploadWorkout("src/test/resources/example.tcx", "Upload Workout");
        workoutDetailsPage.isOpen();
        String downloadedFileName = calendarPage.fileDownload();
        assertTrue(downloadedFileName.endsWith(".tcx"));
    }
}
