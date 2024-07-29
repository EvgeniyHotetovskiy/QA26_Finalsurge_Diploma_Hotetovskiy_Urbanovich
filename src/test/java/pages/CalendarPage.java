package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import io.qameta.allure.Step;
import models.AddWorkout;
import org.openqa.selenium.By;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CalendarPage extends BasePage {
    private static final String QUICK_ADD_TOGGLE = "#QuickAddToggle";
    private static final By TODAY_DAY = By.cssSelector("[class*='fc-today']");
    private static final String PLUS_ON_THE_CALENDAR = ".icon-plus";
    private static final String QUICK_ADD_FROM_THE_CALENDAR = ".quick-add";
    private static final String FULL_ADD_FROM_THE_CALENDAR = ".full-add";
    private static final String DATA_FIELD = "#WorkoutDate";
    private static final String ADD_WORKOUT = "#saveButton";
    private static final String ACTIVITY_TYPE = "#ActivityType";
    private static final By ALERT_ERROR = By.cssSelector(".alert.alert-error");
    private static final String TABLE_DATE_SELECTION = "//table//tr[%d]//td[%d]";
    private static final By QUICK_UPLOAD_BUTTON = By.cssSelector("a.quick-upload");
    private static final By QUICK_DELETE_BUTTON = By.cssSelector("a.quick-delete");
    private static final By DAY_CONTENT = By.cssSelector(".fc-day-content");
    private static final By EVENT_ACTIVITY_TITLE = By.cssSelector(".fc-event-activity-title");
    private static final By FULL_VIEW_BUTTON = By.cssSelector("a.full-view");
    private static final By DOWNLOAD_BUTTON = By.cssSelector("button[onclick*='Delivery/WorkoutSourceFile.cshtml']");
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON = By.cssSelector("a:nth-of-type(1)");
    private static final String WORKOUT_NAME = "#Name";


    @Override
    public void isOpen() {
        $(QUICK_ADD_TOGGLE).shouldBe(clickable);
    }

    @Step("Нажать на добавление тренировки через кнопку 'Быстрое добавление'")
    public void addQuickWorkoutWithButton() {
        $(QUICK_ADD_TOGGLE).click();
    }

    @Step("Нажать на добавление тренировки через кнопку 'Быстрое добавление' с календаря")
    public void addQuickWorkoutFromCalendar() {
        $(PLUS_ON_THE_CALENDAR).click();
        $(QUICK_ADD_FROM_THE_CALENDAR).click();
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
    public boolean activityTypeError() {
        $(ALERT_ERROR).shouldHave(Condition.text("*Please select a valid Activity Type."));
        return true;
    }


    @Step("добавить тренировку (таблица)")
    public void addTrainingLikeTable(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        $(dynamicTableSelection).hover();
        addQuickWorkoutFromCalendar();
    }

    @Step("Созданная тренировка отобразилась в календаре")
    public boolean trainingTableIsDisplayed(int rowIndex, int columnIndex) {

        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        return $(dynamicTableSelection).$(EVENT_ACTIVITY_TITLE).isDisplayed();
    }

    @Step("Редактировать тренировку")
    public void editTrainingLikeTable(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        $(dynamicTableSelection).$(EVENT_ACTIVITY_TITLE).click();
        $(dynamicTableSelection).$(FULL_VIEW_BUTTON).click();

    }


    @Step("Удалить тренировку(таблица)")
    public void deleteWorkout(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        $(dynamicTableSelection).$(EVENT_ACTIVITY_TITLE).click();
        $(QUICK_DELETE_BUTTON).click();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
        $(dynamicTableSelection).$(EVENT_ACTIVITY_TITLE).shouldBe(disappear);
    }

    @Step("Установить дату тренировки")
    public void setWorkoutDate(int daysOffset) {
        LocalDate targetDate = LocalDate.now().plusDays(daysOffset);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        String formattedDate = targetDate.format(formatter);
        $(DATA_FIELD).setValue(formattedDate);
    }

    @Step("Нажать кнопку 'Загрузить тренировку'")
    public void clickUploadWorkout(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        $(dynamicTableSelection).hover();
        $(PLUS_ON_THE_CALENDAR).click();
        $(QUICK_UPLOAD_BUTTON).click();


    }

    public void uploadWorkout() {

        switchTo().frame($("#WorkoutUploadiFrame"));
        $(ADD_WORKOUT).shouldHave(clickable);
        $(WORKOUT_NAME).setValue("Upload Workout");
        String relativePathToFile = "src/test/resources/example.tcx";
        String pathToFile = System.getProperty("user.dir") + File.separator + relativePathToFile;
        File fileUpload = new File(pathToFile);
        $("input[type='file']").uploadFile(fileUpload);
        $(ADD_WORKOUT).click();
        switchTo().defaultContent();
    }

    public boolean downloadButtonIsClickable() {
        $(DOWNLOAD_BUTTON).shouldBe(clickable);
        return true;
    }

    public void fileDownload() {
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        File downloadedFile = $(DOWNLOAD_BUTTON).download();
        assertThat(downloadedFile).hasName("BIKE-2024-06-30.tcx");
    }
}



