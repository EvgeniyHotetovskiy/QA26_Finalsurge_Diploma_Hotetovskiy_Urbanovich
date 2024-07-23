package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.AddWorkoutPage;
import pages.CalendarPage;
import pages.LoginPage;
import utils.DriverFactory;
import utils.PropertyReader;
import utils.TestListener;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

@Listeners({TestListener.class})
public class BaseTest {
//    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CalendarPage calendarPage;
    protected AddWorkoutPage addWorkoutPage;
    protected String BASE_URL = PropertyReader.getProperty("url");
    protected static final String BASE_LOGIN = PropertyReader.getProperty("login");
    protected static final String BASE_PASSWORD = PropertyReader.getProperty("password");

    @BeforeClass(alwaysRun = true)
    @Parameters({"browserName"})
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) {
        this.loginPage = new LoginPage();
        this.calendarPage = new CalendarPage();
        this.addWorkoutPage = new AddWorkoutPage();
        Configuration.baseUrl = BASE_URL;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = browser;
        Configuration.timeout = 10000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

    }
    @BeforeMethod(alwaysRun = true)
    public void openBrowser () {
        open("/");
    }


    @BeforeMethod(onlyForGroups = "withSuccessLogin")
    public void preConditionForGroup() {
        open("/");
        loginPage.login(BASE_LOGIN, BASE_PASSWORD);
    }
    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);

    }
}