package tests;

import models.AddShoes;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ShoesTests extends BaseTest {
    private static final String SHOE_NAME_ERROR =  "This field is required.";

    @Test(groups = {"regression", "smoke", "withSuccessLogin", "deleteShoes"})
    public void positiveAddShoesTest() {
        AddShoes quickAddshoes = new AddShoes.AddShoesBuilder()
                .setShoeName("running shoes")
                .build();
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.quickAddShoesInput(quickAddshoes);
        shoesPage.clickAddShoesButton();
        AddShoes actualAddShoes = shoesPage.getShoesNameFromPage();
        shoesPage.addShoesWait();
        Assert.assertEquals(actualAddShoes.getShoeName(), quickAddshoes.getShoeName(), "Имя обуви не совпадает с ожидаемым");
        shoesPage.clickEditButton();
    }

    @Test(groups = {"regression", "withSuccessLogin", "deleteShoes"})
    public void positiveEditShoesTest() {
        AddShoes quickAddshoes = new AddShoes.AddShoesBuilder()
                .setShoeName("favorite sneakers")
                .build();
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.quickAddShoesInput(quickAddshoes);
        shoesPage.clickAddShoesButton();
        shoesPage.clickEditButton();
        shoesPage.isOpen();
        AddShoes editAddshoes = new AddShoes.AddShoesBuilder()
                .setBrand("adidas")
                .setModel("GAZELLE")
                .setCost("150.00")
                .setDatePurchased("7/2/2024")
                .setSize("9")
                .setStartDistance("0")
                .setStartDistancetype("km")
                .setAlertDistance("500")
                .setAlertDistancetype("km")
                .build();
        shoesPage.editDetailsShoes(editAddshoes);
        shoesPage.clickAddShoesButton();
        shoesPage.clickEditButton();
        AddShoes actualAddShoes = shoesPage.getInfoFromPage();
        shoesPage.addShoesWait();
        Assert.assertEquals(actualAddShoes, editAddshoes, "значения не совпадают с ожидаемым");
    }

    @Test(groups = {"regression", "smoke", "withSuccessLogin"})
    public void negativeAddShoesTest() {
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.clickAddShoesButton();
        Assert.assertEquals(shoesPage.getShoeNameError(),SHOE_NAME_ERROR);
    }
}
