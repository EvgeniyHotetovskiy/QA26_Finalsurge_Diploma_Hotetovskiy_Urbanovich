package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver)
    {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "page source", type = "html")
    public static String attachHtml(WebDriver driver)
    {
        return driver.getPageSource();
    }
}
