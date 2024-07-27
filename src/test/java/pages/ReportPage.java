package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class ReportPage extends BasePage {
    private static final String VIEW_REPORT = "#saveButton";
    private static final String ZONE_REPORT = ("a[href='WorkoutZoneReport']");

    @Override
    public void isOpen() {
        $(VIEW_REPORT).shouldBe(clickable);
    }

    public void startDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = yesterday.format(formatter);
        $("#WorkoutDate").setValue(formattedDate);
    }

    public void endDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = tomorrow.format(formatter);
        $("#WorkoutDateEnd").setValue(formattedDate);
    }

    public void clickViewReport() {
        $(VIEW_REPORT).click();
    }

    public void clickZoneReport() {
        $(ZONE_REPORT).click();
    }

    public boolean reportViewDisplayed() {
        return $(By.xpath("//table//tr[1]//td[2]")).$(By.partialLinkText("Walk")).isDisplayed();
    }

    public boolean zoneWorkoutError() {
        $(".alert.alert-error").shouldHave(Condition.text("*Please select a valid Activity Zone Type."));
        return true;
    }
}
