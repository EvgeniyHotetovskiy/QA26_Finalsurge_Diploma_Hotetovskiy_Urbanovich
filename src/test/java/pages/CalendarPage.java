package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class CalendarPage extends BasePage{
    private static final By CalendarButton = By.linkText("icsw16-day-calendar");
    private static final String QuickAddToggle = "#QuickAddToggle";
    private static final By TodayDate = By.cssSelector("[class*='fc-today']");
    private static final String PlusOntheCalendar = ".icon-plus";
    private static final String QuickAddFromtheCalendar = ".quick-add";
    private static final String FullAddFromtheCalendar = ".full-add";
    private static final String DateField = "#WorkoutDate";
    private static final String AddWorkout = "#saveButton";


   public CalendarPage(WebDriver driver) {
        super(driver);
    }
    @Step ("Открыть страницу 'Календарь'")
    public void openCalendarPage(){
       $(CalendarButton).shouldBe(clickable).click();
    }
    @Step ("Нажать на добавление тренировки через кнопку 'Быстрое добавление'")
    public void addQuickWorkoutWithButton(){
        $(QuickAddToggle).click();

    }
    @Step ("Нажать на добавление тренировки через кнопку 'Быстрое добавление' с календаря")//bug
    public void addQuickWorkoutFromCalendar(){
        $(PlusOntheCalendar).click();
        $(QuickAddFromtheCalendar).click();
    }
    @Step("Ввод даты")
    public void addDate(String date){
        $(DateField).setValue(date);

    }
    @Step("нажать 'добавить тренировку'")
    public void clickAddWorkout(){
        $(AddWorkout).click();
    }


    @Step ("Нажать на добавление тренировки через кнопку 'Расширенное добавление' с календаря")
    public void clickFullWorkoutFromCalendar() {
        $(TodayDate).$(PlusOntheCalendar).hover().click();
        $(TodayDate).$(FullAddFromtheCalendar).shouldBe(visible).hover().click();
    }
    @Step("Выбор типа активности при быстром добавлении")
    public void activityTypeQuickSelect(String activityType) {
        $("#ActivityType").selectOption(activityType);

    }
    @Step("Созданная на сегодня тренировка отобразилась в календаре")
    public boolean WorkOutIsDisplayed() {
         $(TodayDate).$(".fc-day-content").$(".fc-event-activity-title").shouldBe(exist);
        return true;
    }

}


