package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class CalendarPage extends BasePage{
    private static final By CALENDAR_BUTTON = By.linkText("icsw16-day-calendar");
    private static final String QUICK_ADD_TOGGLE = "#QuickAddToggle";
    private static final By TODAY_DATE = By.cssSelector("[class*='fc-today']");
    private static final String PLUS_ON_THE_CALENDAR = ".icon-plus";
    private static final String QUICK_ADD_FROM_THE_CALENDAR = ".quick-add";
    private static final String FULL_ADD_FROM_THE_CALENDAR = ".full-add";
    private static final String DATE_FIELD = "#WorkoutDate";
    private static final String ADD_WORKOUT = "#saveButton";

    @Step ("Открыть страницу 'Календарь'")
    public void openCalendarPage(){
       $(CALENDAR_BUTTON).shouldBe(clickable).click();
    }
    @Step ("Нажать на добавление тренировки через кнопку 'Быстрое добавление'")
    public void addQuickWorkoutWithButton(){
        $(QUICK_ADD_TOGGLE).click();

    }
    @Step ("Нажать на добавление тренировки через кнопку 'Быстрое добавление' с календаря")//bug
    public void addQuickWorkoutFromCalendar(){
        $(PLUS_ON_THE_CALENDAR).click();
        $(QUICK_ADD_FROM_THE_CALENDAR).click();
    }
    @Step("Ввод даты")
    public void addDate(String date){
        $(DATE_FIELD).setValue(date);

    }
    @Step("нажать 'добавить тренировку'")
    public void clickAddWorkout(){
        $(ADD_WORKOUT).click();
    }


    @Step ("Нажать на добавление тренировки через кнопку 'Расширенное добавление' с календаря")
    public void clickFullWorkoutFromCalendar() {
        $(TODAY_DATE).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DATE).$(FULL_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
    }
    @Step("Выбор типа активности при быстром добавлении")
    public void activityTypeQuickSelect(String activityType) {
        $("#ActivityType").selectOption(activityType);

    }
    @Step("Созданная на сегодня тренировка отобразилась в календаре")
    public boolean WorkOutIsDisplayed() {
         $(TODAY_DATE).$(".fc-day-content").$(".fc-event-activity-title").shouldBe(exist);
        return true;
    }

}


