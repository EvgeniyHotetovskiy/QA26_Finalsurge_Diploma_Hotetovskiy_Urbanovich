package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalendarPage extends BasePage{
    private static final By CalendarButton = By.linkText("icsw16-day-calendar");
    public CalendarPage(WebDriver driver) {
        super(driver);
    }
    @Step ("Открыть страницу 'Календарь'")
    public void openCalendarPage(){
        open();

    }

}
