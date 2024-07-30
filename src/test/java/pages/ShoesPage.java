package pages;


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
    private static final By TABLE_DATE_SELECTION = By.xpath("//table//tr//td[2]");
    private static final String DIST_ALERT_FIELD = ".label.label-inverse";
    private static final String ERROR_MESSAGE = ".error";
    private static final String SELECT_SHOE_BRAND = "#s2id_ShoeBrand";
    private static final String SHOE_BRAND = "#ShoeBrand";
    private static final String SHOE_MODEL = "#ShoeModel";
    private static final String SHOE_COST = "#ShoeCost";
    private static final String SHOE_DATE = "#ShoeDate";
    private static final String SHOE_SIZE = "#ShoeSize";
    private static final String START_DIST = "#StartDist";
    private static final String DIST_TYPE = "#DistType";
    private static final String DIST_ALERT = "#DistAlert";
    private static final String DIST_ALERT_TYPE = "#DistAlertType";


    @Override
    public void isOpen() {
        $(ADD_SHOES_BUTTON).shouldBe(clickable);
    }

    @Step("Перейти на страницу с обувью")
    public void clickShoesPage() {
        $(EQUIPMENT_PAGE).hover();
        $(SHOES_PAGE).click();
    }

    public void clickAddShoesButton() {
        $(ADD_SHOES_BUTTON).click();
    }

    public String getShoeNameError() {
        return $(ERROR_MESSAGE).getText();
    }

    @Step("Нажать на кнопку 'редактировать'")
    public void clickEditButton() {
        $(EDIT_BUTTON).click();
    }

    public void addshoesWait() {
        $(EDIT_BUTTON).shouldBe(clickable);
    }

    @Step("Нажать на кнопку 'удалить'")
    public void clickDeleteButton() {
        $(DELETE_BUTTON).click();
    }


    @Step("Выбор типа активности при быстром добавлении")
    public void quickAddshoesInput(AddShoes quickAddshoes) {
        $(ADD_SHOE_NAME).setValue(quickAddshoes.getShoeName());

    }

    @Step("Получить данные {ShoeName} со страницы")
    public AddShoes getShoesNameFromPage() {
        String shoeNameFromPage = $(TABLE_DATE_SELECTION).getText();
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
        $(SELECT_SHOE_BRAND).click();
        $(SHOE_BRAND).selectOption(editAddshoes.getBrand());
        $(SHOE_MODEL).setValue(editAddshoes.getModel());
        $(SHOE_COST).setValue(editAddshoes.getCost());
        $(SHOE_DATE).setValue(editAddshoes.getDatePurchased());
        $(SHOE_SIZE).selectOption(editAddshoes.getSize());
        $(START_DIST).setValue(editAddshoes.getStartDistance());
        $(DIST_TYPE).selectOption(editAddshoes.getStartDistancetype());
        $(DIST_ALERT).setValue(editAddshoes.getAlertDistance());
        $(DIST_ALERT_TYPE).selectOption(editAddshoes.getAlertDistancetype());
    }

    @Step("Получить данные со страницы")
    public AddShoes getInfoFromPage() {
        AddShoes resultAddShoes = new AddShoes.AddShoesBuilder()
                .setBrand($(SHOE_BRAND).getText())
                .setModel($(SHOE_MODEL).getValue())
                .setCost($(SHOE_COST).getValue())
                .setDatePurchased($(SHOE_DATE).getValue())
                .setSize($(SHOE_SIZE).getText())
                .setStartDistance($(START_DIST).getValue())
                .setStartDistancetype($(DIST_TYPE).getText())
                .setAlertDistance($(DIST_ALERT).getValue())
                .setAlertDistancetype($(DIST_ALERT_TYPE).getText())
                .build();
        return resultAddShoes;
    }

}
