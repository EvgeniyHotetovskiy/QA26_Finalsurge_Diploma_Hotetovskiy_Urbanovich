package tests;


import models.AddWorkout;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CalendarTests extends BaseTest {
    @Test(groups = {"withSuccessLogin", "regression", "smoke"})
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
        calendarPage.deleteTodayWorkout();
        Assert.assertFalse(calendarPage.workOutIsDisplayed());

    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void negativeAddQuickWorkout() {
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.clickAddWorkout();
        Assert.assertTrue(calendarPage.activityTypeError(), "*Please select a valid Activity Type.");
    }

    @Test(groups = {"withSuccessLogin", "regression", "smoke"})
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
        calendarPage.deleteTodayWorkout();
    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void editWorkout() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Swim")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addTrainingLikeTable();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        Assert.assertTrue(calendarPage.trainingTableIsDisplayed());
        calendarPage.editTrainingLikeTable();
        wourkoutDetailsPage.isOpen();
        wourkoutDetailsPage.clickUpdateDWourkout();
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
        wourkoutDetailsPage.editWourkout(editWorkout);
        wourkoutDetailsPage.clickSaveUpdateDWourkout();
        wourkoutDetailsPage.isOpen();
        dashboardPage.clickCalendar();
        Assert.assertTrue(calendarPage.trainingTableIsDisplayed());
        calendarPage.deleteWorkout();


    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void viewFutureTrainingFromDashbordPage() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Bike")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.setTomorrowWorkoutDate();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        dashboardPage.clickDashboardPage();
        dashboardPage.clickUpcomingWorkouts();
        Assert.assertTrue(dashboardPage.upcomingWorkoutsExists());
        dashboardPage.clickDetailsWourkout();
        wourkoutDetailsPage.isOpen();
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();
    }

    @Test(groups = {"withSuccessLogin", "regression"})
    public void viewPastTrainingFromDashbordPage() {
        AddWorkout quickWorkout = new AddWorkout.AddWorkoutBuilder()
                .setActivityType("Walk")
                .build();
        dashboardPage.clickCalendar();
        calendarPage.isOpen();
        calendarPage.addQuickWorkoutWithButton();
        calendarPage.setYesterdayWorkoutDate();
        calendarPage.activityTypeQuickSelect(quickWorkout);
        calendarPage.clickAddWorkout();
        dashboardPage.clickDashboardPage();
        dashboardPage.clickPastWorkouts();
        Assert.assertTrue(dashboardPage.pastWorkoutsExists());
        dashboardPage.clickDetailsWourkout();
        wourkoutDetailsPage.isOpen();
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();
        calendarPage.isOpen();
        dashboardPage.clickDashboardPage();
        Assert.assertTrue(dashboardPage.pastWorkoutEmpty());

    }

    @Test(groups = {"regression", "smoke", "withSuccessLogin"})
    public void fileUploadTest() {
        calendarPage.isOpen();
        calendarPage.clickUploadWorkout();
        calendarPage.uploadWorkout();
        wourkoutDetailsPage.isOpen();
        Assert.assertTrue(calendarPage.downloadButtonIsDisplayed());
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();

    }

    @Test(groups = {"regression", "smoke", "withSuccessLogin"})
    public void fileDownloadTest() {
        calendarPage.isOpen();
        calendarPage.clickUploadWorkout();
        calendarPage.uploadWorkout();
        wourkoutDetailsPage.isOpen();
        calendarPage.fileDownload();
        wourkoutDetailsPage.clickUpdateDWourkout();
        addWorkoutPage.deleteWorkout();
    }

}
