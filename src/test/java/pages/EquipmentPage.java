package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class EquipmentPage extends BasePage {

    private static final By SHOES_PAGE = By.cssSelector("a[href='EquipmentShoes.cshtml']");

    @Override
    public void isOpen() {
        $(SHOES_PAGE).shouldBe(clickable);
    }
}
