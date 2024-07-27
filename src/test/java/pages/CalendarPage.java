package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import models.AddWorkout;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class CalendarPage extends BasePage {
    private static final By CALENDAR_BUTTON = By.linkText("icsw16-day-calendar");
    private static final String QUICK_ADD_TOGGLE = "#QuickAddToggle";
    private static final By TODAY_DAY = By.cssSelector("[class*='fc-today']");
    private static final String PLUS_ON_THE_CALENDAR = ".icon-plus";
    private static final String QuickAddFromtheCalendar = ".quick-add";
    private static final String FullAddFromtheCalendar = ".full-add";
    private static final String DateField = "#WorkoutDate";
    private static final String ADD_WORKOUT = "#saveButton";

    @Override
    public void isOpen() {
        $(QUICK_ADD_TOGGLE).shouldBe(clickable);
    }

    @Step("Открыть страницу 'Календарь'")
    public void openCalendarPage() {
        $(CALENDAR_BUTTON).shouldBe(clickable).click();
    }

    @Step("Нажать на добавление тренировки через кнопку 'Быстрое добавление'")
    public void addQuickWorkoutWithButton() {
        $(QUICK_ADD_TOGGLE).click();
    }

    @Step("Нажать на добавление тренировки через кнопку 'Быстрое добавление' с календаря")//bug на русском языке
    public void addQuickWorkoutFromCalendar() {
        $(PLUS_ON_THE_CALENDAR).click();
        $(QuickAddFromtheCalendar).click();
    }

    @Step("Ввод даты")
    public void addDate(String date) {
        $(DateField).setValue(date);
    }

    @Step("Удаление сегодняшней тренировки")
    public void deleteTodayWorkout() {
        $(TODAY_DAY).$(".fc-day-content").$(".fc-event-activity-title").click();
        $(TODAY_DAY).$("a.quick-delete").click();
        $(".modal-footer").$("a:nth-of-type(1)").click();
        $(TODAY_DAY).$(".fc-day-content").$(".fc-event-activity-title").shouldBe(disappear);
    }


    @Step("нажать 'добавить тренировку'")
    public void clickAddWorkout() {
        $(ADD_WORKOUT).click();
    }


    @Step("Нажать на добавление тренировки через кнопку 'Расширенное добавление' с календаря")
    public void clickFullWorkoutFromCalendar() {
        $(TODAY_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DAY).$(FullAddFromtheCalendar).shouldBe(visible).hover().click();
    }

    @Step("Выбор типа активности при быстром добавлении")
    public void activityTypeQuickSelect(AddWorkout quickWorkout) {
        $("#ActivityType").selectOption(quickWorkout.getActivityType());
    }

    @Step("Созданная на сегодня тренировка отобразилась в календаре")
    public boolean workOutIsDisplayed() {
        return $(TODAY_DAY).$(".fc-day-content").$(".fc-event-activity-title").isDisplayed();
    }

    @Step("Ошибка при незаполненом типе активности")
    public boolean activityTypeError() {
        $(".alert.alert-error").shouldHave(Condition.text("*Please select a valid Activity Type."));
        return true;
    }

    @Step("проверка отображения тренировки в календаре")
    public boolean trainingIsDisplayed(String workoutName) {
        $(".fc-event-activity-title").shouldHave(Condition.text(workoutName));
        return true;
    }
    @Step("добавить тренировку (таблица)")
    public void addTrainingLikeTable() {
        $(By.xpath("//table//tr[1]//td[1]")).hover();
        addQuickWorkoutFromCalendar();
    }

    @Step("Созданная тренировка отобразилась в календаре")
    public boolean trainingTableIsDisplayed() {
        return $(By.xpath("//table//tr[1]//td[1]")).$(".fc-event-activity-title").isDisplayed();
    }

    @Step("Редактировать тренировку")
    public void editTrainingLikeTable() {
        $(By.xpath("//table//tr[1]//td[1]")).$(".fc-event-activity-title").click();
        $(By.xpath("//table//tr[1]//td[1]")).$("a.full-view").click();
    }

    @Step("Установить завтрашнюю дату тренировки")
    public void setTomorrowWorkoutDate() {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = tomorrow.format(formatter);
        $("#WorkoutDate").setValue(formattedDate);
    }

    @Step("Установить вчерашнюю дату тренировки")
    public void setYesterdayWorkoutDate() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = yesterday.format(formatter);
        $("#WorkoutDate").setValue(formattedDate);
    }
}


