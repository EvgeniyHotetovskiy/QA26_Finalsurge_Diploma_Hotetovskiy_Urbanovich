package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import models.AddShoes;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

public class ShoesPage extends BasePage {
    private static final By ADD_SHOES_BUTTON = By.cssSelector("#saveButton");
    private static final By SHOES_PAGE = By.cssSelector("a[href='EquipmentShoes.cshtml']");
    private static final By EQUIPMENT_PAGE = By.cssSelector("a[href='Equipment.cshtml']");

    @Override
    public void isOpen() {
        $(ADD_SHOES_BUTTON).shouldBe(clickable);
    }

    public void clickShoesPage() {
        $(EQUIPMENT_PAGE).hover();
        $(SHOES_PAGE).click();
    }

    public void clickAddShoesButton() {
        $(ADD_SHOES_BUTTON).click();
    }

    public boolean shoeNameError() {
        $(".error").shouldHave(Condition.text("This field is required."));
        return true;
    }

    public void clickEditButton() {
        $(".btn.btn-mini").click();
    }

    public void addshoesWait() {
        $(".btn.btn-mini").shouldBe(clickable);
    }

    public void clickDeleteButton() {
        $("#del-shoe").click();
    }

    public void addShoeName() {
        $("#ShoeName").click();

    }

    @Step("Выбор типа активности при быстром добавлении")
    public void quickAddshoesInput(AddShoes quickAddshoes) {
        $("#ShoeName").setValue(quickAddshoes.getShoeName());

    }

    @Step("Получить данные {ShoeName} со страницы")
    public AddShoes getShoesNameFromPage() {
        String shoeNameFromPage = $(By.xpath("//table//tr//td[2]")).getText();
        return new AddShoes.AddShoesBuilder()
                .setShoeName(shoeNameFromPage)
                .build();
    }

    @Step("Удаление обуви")
    public void deleteShoes() {
        clickDeleteButton();
        $(".modal-footer").$("a:nth-of-type(1)").click();
        $(".fc-day-content").$(".fc-event-activity-title").shouldBe(disappear);
    }

    @Step("Редактирование обуви, добавление информации")
    public void editDetailsShoes(AddShoes editAddshoes) {
        $("#s2id_ShoeBrand").click();
        $("#ShoeBrand").selectOption(editAddshoes.getBrand());

        $("#ShoeModel").setValue(editAddshoes.getModel());
        $("#ShoeCost").setValue(editAddshoes.getCost());
        $("#ShoeDate").setValue(editAddshoes.getDatePurchased());
        $("#ShoeSize").selectOption(editAddshoes.getSize());

        $("#StartDist").setValue(editAddshoes.getStartDistance());
        $("#DistType").selectOption(editAddshoes.getStartDistancetype());
        $("#DistAlert").setValue(editAddshoes.getAlertDistance());
        $("#DistAlertType").selectOption(editAddshoes.getAlertDistancetype());
    }

    @Step("Проверка отображения информации после редактирования")
    public boolean editInfoIsDisplayed() {
        $(By.xpath("//table//tr//td[2]")).$(".label.label-inverse").shouldHave(Condition.text("Alert at: 500 km"));
        return true;
    }

}
