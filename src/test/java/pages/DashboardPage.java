package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends BasePage {
    private static final By DASHBOARD_BUTTON = By.cssSelector("a[href='Default.cshtml'].ptip_s");
    private static final By CALENDAR_PAGE = By.cssSelector("a[href='Calendar.cshtml'].ptip_s");
    private static final By CALCULATOR_PAGE = By.cssSelector("a[href='#'].ptip_s");
    private static final By WORKOUT_REPORT_PAGE = By.cssSelector("a[href='WorkoutReport.cshtml'].ptip_s");
    private static final String VIEW_CALENDAR = "#workout-add";
    private static final String UPCOMING_WORKOUTS = ".w-box-header";
    private static final By LOGOUT_BUTTON = By.cssSelector("a[href='logout.cshtml']");
    private static final By WORKOUT_DETAILS_PAGE = By.cssSelector("a[href^='WorkoutDetails.cshtml?id='");
    private static final By PAST_WORKOUTS = By.cssSelector("div[data-label='past-workouts']");
    private static final By UPCOMING_WORKOUTS_VIEW = By.cssSelector(".w-box.w-box-green.hideable");
    private static final String WORKOUT_DETAILS_VIEW = ".dont-break-out";
    private static final String PAST_WORKOUTS_DETAILS = ".minor";

    @Override
    public void isOpen() {
        $(VIEW_CALENDAR).shouldBe(clickable);
    }

    @Step("Открыть страницу 'Календарь'")
    public void clickCalendar() {
        $(CALENDAR_PAGE).click();
    }

    @Step("Открыть страницу 'Калькулятор'")
    public void clickCalculator() {
        $(CALCULATOR_PAGE).click();
    }

    @Step("Открыть домашнюю страницу")
    public void clickDashboardPage() {
        $(DASHBOARD_BUTTON).click();
    }

    @Step("Открыть страницу 'Отчёты'")
    public void clickWorkoutReportPage() {
        $(WORKOUT_REPORT_PAGE).click();
    }

    @Step("Открыть страницу 'Отчёты'")
    public void clickUpcomingWorkouts() {
        $(UPCOMING_WORKOUTS_VIEW).$(UPCOMING_WORKOUTS).click();
    }

    public boolean upcomingWorkoutsExists() {
        return $(UPCOMING_WORKOUTS_VIEW).$(WORKOUT_DETAILS_VIEW).exists();
    }

    public void clickDetailsWorkout() {
        $(WORKOUT_DETAILS_PAGE).click();
    }

    public void clickPastWorkouts() {

        $(PAST_WORKOUTS).click();
    }

    public boolean pastWorkoutsExists() {
        return $(PAST_WORKOUTS).$(WORKOUT_DETAILS_VIEW).exists();
    }

    public String pastWorkoutEmpty() {
        return $(PAST_WORKOUTS).$(PAST_WORKOUTS_DETAILS).getText();
    }

    @Step("Выйти из приложения")
    public void clickLogoutButton() {
        $(LOGOUT_BUTTON).click();
    }
}
