package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class AddWorkoutPage extends BasePage{

    @Step("Выбор типа активности {typeActivity}, {subtypeActivity} 'расширенное добавление'")
    public void activityTypeSelect(String typeActivity, String subtypeActivity) {
        $(By.cssSelector("a[data-code='" + typeActivity + "']")).shouldBe(clickable).click();
        $(By.xpath("//a[text()='" + subtypeActivity + "']")).shouldBe(clickable).click();
    }

}
