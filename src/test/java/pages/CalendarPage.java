package pages;

import io.qameta.allure.Step;
import models.AddWorkout;
import org.openqa.selenium.By;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;


public class CalendarPage extends BasePage {
    private static final String QUICK_ADD_TOGGLE = "#QuickAddToggle";
    private static final By TODAY_DAY = By.cssSelector("[class*='fc-today']");
    private static final String PLUS_ON_THE_CALENDAR = ".icon-plus";
    private static final String QUICK_ADD_FROM_THE_CALENDAR = "a.quick-add";
    private static final String FULL_ADD_FROM_THE_CALENDAR = ".full-add";
    private static final String DATA_FIELD = "#WorkoutDate";
    private static final String ADD_WORKOUT = "#saveButton";
    private static final String ACTIVITY_TYPE = "#ActivityType";
    private static final By ALERT_ERROR = By.cssSelector(".alert.alert-error");
    private static final By QUICK_UPLOAD_BUTTON = By.cssSelector("a.quick-upload");
    private static final By QUICK_DELETE_BUTTON = By.cssSelector("a.quick-delete");
    private static final By DAY_CONTENT = By.cssSelector(".fc-day-content");
    private static final By EVENT_ACTIVITY_TITLE = By.cssSelector(".fc-event-activity-title");
    private static final By FULL_VIEW_BUTTON = By.cssSelector("a.full-view");
    private static final By DOWNLOAD_BUTTON = By.cssSelector("button[onclick*='Delivery/WorkoutSourceFile.cshtml']");
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON = By.cssSelector("a:nth-of-type(1)");
    private static final String WORKOUT_NAME = "#Name";
    private static final String WORKOUT_UPLOAD_FRAME = "#WorkoutUploadiFrame";
    private static final By INPUT_FILE = By.cssSelector("input[type='file']");


    @Override
    public void isOpen() {
        $(QUICK_ADD_TOGGLE).shouldBe(clickable);
    }

    @Step("Нажать на добавление тренировки через кнопку 'Быстрое добавление'")
    public void addQuickWorkoutWithButton() {
        $(QUICK_ADD_TOGGLE).click();
    }

    @Step("Нажать на добавление сегодняшней тренировки через кнопку 'Быстрое добавление' с календаря")
    public void addQuickWorkoutFromCalendar() {
        $(TODAY_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DAY).$(QUICK_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
    }

    @Step("Нажать на 'плюс' с календаря")
    public void clickPlusFromCalendar() {
        $(TODAY_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
    }

    @Step("Удаление сегодняшней тренировки")
    public void deleteTodayWorkout() {
        $(TODAY_DAY).$(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).click();
        $(TODAY_DAY).$(QUICK_DELETE_BUTTON).click();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
        $(TODAY_DAY).$(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).shouldBe(disappear);
    }


    @Step("нажать 'добавить тренировку'")
    public void clickAddWorkout() {
        $(ADD_WORKOUT).click();
    }


    @Step("Нажать на добавление тренировки через кнопку 'Расширенное добавление' с календаря")
    public void clickFullWorkoutFromCalendar() {
        $(TODAY_DAY).$(PLUS_ON_THE_CALENDAR).hover().click();
        $(TODAY_DAY).$(FULL_ADD_FROM_THE_CALENDAR).shouldBe(visible).hover().click();
    }

    @Step("Выбор типа активности при быстром добавлении")
    public void activityTypeQuickSelect(AddWorkout quickWorkout) {
        $(ACTIVITY_TYPE).selectOption(quickWorkout.getActivityType());
    }

    @Step("Созданная на сегодня тренировка отобразилась в календаре")
    public boolean workOutIsDisplayed() {
        return $(TODAY_DAY).$(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).isDisplayed();
    }

    @Step("Ошибка при незаполненом типе активности")
    public String activityTypeError() {
        return $(ALERT_ERROR).getText();
    }

    @Step("Редактировать тренировку")
    public void editTraining() {
        $(EVENT_ACTIVITY_TITLE).click();
        $(FULL_VIEW_BUTTON).click();
    }


    @Step("Установить дату тренировки")
    public void setWorkoutDate(int daysOffset) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(DATA_FIELD).setValue(formattedDate);
    }

    @Step("Нажать кнопку 'Загрузить тренировку'")
    public void clickUploadWorkout() {
        $(TODAY_DAY).$(QUICK_UPLOAD_BUTTON).click();
    }

    @Step("Загрузить тренировку")
    public void uploadWorkout(String relativePathToFile, String Workout) {

        switchTo().frame($(WORKOUT_UPLOAD_FRAME));
        $(ADD_WORKOUT).shouldHave(clickable);
        $(WORKOUT_NAME).setValue(Workout);
        String pathToFile = System.getProperty("user.dir") + File.separator + relativePathToFile;
        File fileUpload = new File(pathToFile);
        $(INPUT_FILE).uploadFile(fileUpload);
        $(ADD_WORKOUT).click();
        switchTo().defaultContent();
    }

    @Step("нажать на кнопку 'Скачать тренировку'")
    public void downloadButtonIsClickable() {
        $(DOWNLOAD_BUTTON).shouldBe(clickable);
    }

    @Step("Скачать тренировку")
    public String fileDownload() {
        File downloadedFile = $(DOWNLOAD_BUTTON).download();
        return downloadedFile.getName();

    }
}


