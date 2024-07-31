package pages;

import io.qameta.allure.Step;
import models.Calculator;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class CalculatorPage extends BasePage {
    private static final String SAVE_BUTTON = "#saveButtonSettings";
    private static final By TINMAN_BUTTON = By.cssSelector("a[href^='https://log.finalsurge.com/TinmanCalc.cshtml']");
    private static final By RACE_DISTANCE = By.cssSelector("[name='distance']");
    private static final String GENDER_BUTTON = "#Male";
    private static final By ALERT_ERROR = By.cssSelector(".alert.alert-error");
    private static final By RACE_SPLIT = By.cssSelector(".w-box");
    private static final String EVENT_TYPE = "#FIVEK";
    private static final String TIME_HH = "#TimeHH";
    private static final String TIME_MM = "#TimeMM";
    private static final String TIME_SS = "#TimeSS";


    @Override
    public void isOpen() {
        switchTo().frame(0);
        {
            $(SAVE_BUTTON).shouldBe(clickable);
        }
    }

    @Step("Ввод времени")
    public void inputIntencityCalcTime(Calculator intensityCalc) {
        $(TIME_HH).setValue(intensityCalc.getHours());
        $(TIME_MM).setValue(intensityCalc.getMinuts());
        $(TIME_SS).setValue(intensityCalc.getSeconds());
    }

    @Step("Выбрать калькулятор Tinman")
    public void clickTinmanCalc() {
        $(TINMAN_BUTTON).click();
    }

    @Step("Заполнение калькулятора")
    public void selectEvent() {
        $(EVENT_TYPE).click();
    }

    @Step("Заполнение калькулятора")
    public void selectTinmanRaceDistance(String distance) {
        $(RACE_DISTANCE).selectOption(distance);
    }

    @Step("Нажать рассчитать")
    public void clickCalcPaces() {
        $(SAVE_BUTTON).click();
    }

    @Step("Заполнить гендер")
    public void clickGenderButton() {
        $(GENDER_BUTTON).click();
    }

    public boolean workoutSplitdisplayed() {
        return $(RACE_SPLIT).should(exist).isDisplayed();
    }

    @Step("Ошибка при незаполненом типе активности")
    public String intencityCalcError() {
        return $(ALERT_ERROR).text();
    }
}
