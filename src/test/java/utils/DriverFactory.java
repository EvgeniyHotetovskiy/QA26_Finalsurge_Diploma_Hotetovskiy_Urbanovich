package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {
    public static WebDriver getDriver(String browserName) throws Exception
    {
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (PropertyReader.getProperty("headless").equals("true")) {
                    options.addArguments("--headless");
                }
                driver = new ChromeDriver(options);
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Unsupported browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

}
