package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends BasePage {
    private static final By DASHBOARD_BUTTON = By.cssSelector("a[href='Default.cshtml'].ptip_s");
    private static final By CALENDAR_PAGE = By.cssSelector("a[href='Calendar.cshtml'].ptip_s");
    private static final By CALCULATOR_PAGE = By.cssSelector("a[href='#'].ptip_s");
    private static final By WORKOUT_REPORT_PAGE = By.cssSelector("a[href='WorkoutReport.cshtml'].ptip_s");
    private static final String VIEW_CALENDAR = ".btn.btn-small.btn-info.ptip_s";
    private static final String UPCOMING_WORKOUTS = ".w-box-header";
    private static final By LOGOUT_BUTTON = By.cssSelector("a[href='logout.cshtml']");
    private static final By EQUIPMENT_PAGE = By.cssSelector("a[href='Equipment.cshtml']");

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
    @Step("Открыть страницу 'Снаряжение'")
    public void clickEquipmentPage() {
        $(EQUIPMENT_PAGE).click();
    }



    @Step("Открыть страницу 'Отчёты'")
    public void clickUpcomingWorkouts() {
        $(".w-box.w-box-green.hideable").$(UPCOMING_WORKOUTS).click();
    }

    public boolean UpcomingWorkoutsExists() {
        return $(".w-box.w-box-green.hideable").$(".dont-break-out").exists();
    }

    public void clickDetailsWourkout() {
        $("a[href^='WorkoutDetails.cshtml?id=']").click();
    }

    public void clickPastWorkouts() {

        $(By.cssSelector("div[data-label='past-workouts']")).click();
    }

    public boolean PastWorkoutsExists() {
        return $(By.cssSelector("div[data-label='past-workouts']")).$(".dont-break-out").exists();
    }

    public boolean PastWorkoutEmpty() {
        $(By.cssSelector("div[data-label='past-workouts']")).$(".minor").shouldHave(Condition.text("You have no past workouts within the last 14 days."));
        return true;
    }
    public void clickLogoutButton(){
        $(LOGOUT_BUTTON).click();
    }
}
