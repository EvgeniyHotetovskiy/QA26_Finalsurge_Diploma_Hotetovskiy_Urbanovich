package pages;

import io.qameta.allure.Step;
import models.AddWorkout;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class AddWorkoutPage extends BasePage {
    private static final String ADD_WORKOUT = "#saveButton";
    private static final String WORKOUT_NAME = "#Name";
    private static final By CUSTOMIZE_SETTINGS = By.cssSelector("a[href='WorkoutCustomize.cshtml?id=']");
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON = By.cssSelector("a:nth-of-type(1)");
    private static final String DELETE_BUTTON = "#del-workout";

    @Override
    public void isOpen() {
        $(CUSTOMIZE_SETTINGS).shouldBe(clickable);
    }

    @Step("Выбор типа активности {typeActivity}, {subtypeActivity} 'расширенное добавление'")
    public void activityTypeSelect(String typeActivity, String subtypeActivity) {
        $(By.cssSelector("a[data-code='" + typeActivity + "']")).shouldBe(clickable).click();
        $(By.xpath("//a[text()='" + subtypeActivity + "']")).shouldBe(clickable).click();
    }

    @Step("нажать 'добавить тренировку'")
    public void clickAddWorkout() {
        $(ADD_WORKOUT).click();
    }

    @Step("добавить название тренировки")
    public void addWorkoutName(AddWorkout fullWorkout) {
        $(WORKOUT_NAME).setValue(fullWorkout.getName());
    }

    @Step("получить название тренировки")
    public String getWorkoutName() {
        return $(".formSep").$("div:nth-of-type(3)").text();
    }

    @Step("Удалить тренировку")
    public void deleteWorkout() {
        $(DELETE_BUTTON).click();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
    }

}
