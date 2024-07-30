package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class ReportPage extends BasePage {
    private static final String VIEW_REPORT = "#saveButton";
    private static final String ZONE_REPORT = ("a[href='WorkoutZoneReport']");
    private static final String WORKOUT_START_DATE_FIELD = "#WorkoutDate";
    private static final String WORKOUT_END_DATE_FIELD = "#WorkoutDateEnd";
    private static final String ALERT_ERROR = ".alert.alert-error";
    private static final By ACTIVITY_TYPE = By.xpath("//table//tr[1]//td[2]");

    @Override
    public void isOpen() {
        $(VIEW_REPORT).shouldBe(clickable);
    }


    @Step("Установить дату")
    private void setDate(int daysOffset, String selector) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(selector).setValue(formattedDate);
    }

    @Step("Установить начальную дату")
    public void setStartDate(int daysOffset) {
        setDate(daysOffset, WORKOUT_START_DATE_FIELD);
    }

    @Step("Установить конечную дату")
    public void setEndDate(int daysOffset) {
        setDate(daysOffset, WORKOUT_END_DATE_FIELD);
    }


    @Step("Просмотреть ViewReport")
    public void clickViewReport() {
        $(VIEW_REPORT).click();
    }

    @Step("Просмотреть ZoneReport")
    public void clickZoneReport() {
        $(ZONE_REPORT).click();
    }

    public boolean reportViewDisplayed() {
        return $(ACTIVITY_TYPE).$(By.partialLinkText("Walk")).isDisplayed();
    }

    public String zoneWorkoutError() {
        return $(ALERT_ERROR).getText();
    }
}

