package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage() {

    }

    public abstract void isOpen();

}
