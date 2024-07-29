package tests;

import models.AddShoes;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ShoesTests extends BaseTest {
    @Test(groups = {"regression", "smoke", "withSuccessLogin"})
    public void positiveAddShoesTest() {
        AddShoes quickAddshoes = new AddShoes.AddShoesBuilder()
                .setShoeName("running shoes")
                .build();
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.quickAddshoesInput(quickAddshoes);
        shoesPage.clickAddShoesButton();
        AddShoes actualAddShoes = shoesPage.getShoesNameFromPage();
        shoesPage.addshoesWait();
        Assert.assertEquals(actualAddShoes.getShoeName(), quickAddshoes.getShoeName(), "Имя обуви не совпадает с ожидаемым");
        shoesPage.clickEditButton();
        shoesPage.deleteShoes();
    }

    @Test(groups = {"regression", "withSuccessLogin"})
    public void positiveEditShoesTest() {
        AddShoes quickAddshoes = new AddShoes.AddShoesBuilder()
                .setShoeName("favorite sneakers")
                .build();
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.quickAddshoesInput(quickAddshoes);
        shoesPage.clickAddShoesButton();
        shoesPage.clickEditButton();
        shoesPage.isOpen();
        AddShoes editAddshoes = new AddShoes.AddShoesBuilder()
                .setBrand("adidas")
                .setModel("GAZELLE")
                .setCost("150")
                .setDatePurchased("07/02/2024")
                .setSize("9")
                .setStartDistance("0")
                .setStartDistancetype("km")
                .setAlertDistance("500")
                .setAlertDistancetype("km")
                .build();
        shoesPage.editDetailsShoes(editAddshoes);
        shoesPage.clickAddShoesButton();
        shoesPage.addshoesWait();
        Assert.assertEquals(shoesPage.editInfoIsDisplayed(), "Alert at: 500 km");
        shoesPage.clickEditButton();
        shoesPage.deleteShoes();
    }


    @Test(groups = {"regression", "smoke", "withSuccessLogin"})
    public void negativeAddShoesTest() {
        calendarPage.isOpen();
        shoesPage.clickShoesPage();
        shoesPage.isOpen();
        shoesPage.clickAddShoesButton();
        Assert.assertTrue(shoesPage.getShoeNameError());
    }
}
