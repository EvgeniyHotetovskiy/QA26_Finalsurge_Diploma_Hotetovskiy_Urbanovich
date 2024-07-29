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
    private static final By EDIT_BUTTON = By.cssSelector(".btn.btn-mini");
    private static final By DELETE_BUTTON = By.cssSelector("#del-shoe");
    private static final String ADD_SHOE_NAME = "#ShoeName";
    private static final By MODAL_VIEW = By.cssSelector(".modal-footer");
    private static final By DELETE_CONFIRM_BUTTON = By.cssSelector("a:nth-of-type(1)");
    private static final By DAY_CONTENT = By.cssSelector(".fc-day-content");
    private static final By EVENT_ACTIVITY_TITLE = By.cssSelector(".fc-event-activity-title");
    private static final String TABLE_DATE_SELECTION = "//table//tr[%d]//td[%d]";
    private static final String DIST_ALERT_FIELD = ".label.label-inverse";
    private static final String ERROR_MESSAGE = ".error";


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

    public boolean getShoeNameError() {
        $(ERROR_MESSAGE).shouldHave(Condition.text("This field is required."));
        return true;
    }

    public void clickEditButton() {
        $(EDIT_BUTTON).click();
    }

    public void addshoesWait() {
        $(EDIT_BUTTON).shouldBe(clickable);
    }

    public void clickDeleteButton() {
        $(DELETE_BUTTON).click();
    }


    @Step("Выбор типа активности при быстром добавлении")
    public void quickAddshoesInput(AddShoes quickAddshoes) {
        $(ADD_SHOE_NAME).setValue(quickAddshoes.getShoeName());

    }

    @Step("Получить данные {ShoeName} со страницы")
    public AddShoes getShoesNameFromPage(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        String shoeNameFromPage = $(dynamicTableSelection).getText();
        return new AddShoes.AddShoesBuilder()
                .setShoeName(shoeNameFromPage)
                .build();
    }

    @Step("Удаление обуви")
    public void deleteShoes() {
        clickDeleteButton();
        $(MODAL_VIEW).$(DELETE_CONFIRM_BUTTON).click();
        $(DAY_CONTENT).$(EVENT_ACTIVITY_TITLE).shouldBe(disappear);
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
    public String editInfoIsDisplayed(int rowIndex, int columnIndex) {
        By dynamicTableSelection = By.xpath(String.format(TABLE_DATE_SELECTION, rowIndex, columnIndex));
        return $(dynamicTableSelection).$(DIST_ALERT_FIELD).text();

    }

}
