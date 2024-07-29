package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;
import utils.TestListener;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Listeners({TestListener.class})
public class BaseTest {
    protected static final String BASE_LOGIN = PropertyReader.getProperty("login");
    protected static final String BASE_PASSWORD = PropertyReader.getProperty("password");
    protected LoginPage loginPage;
    protected CalendarPage calendarPage;
    protected AddWorkoutPage addWorkoutPage;
    protected DashboardPage dashboardPage;
    protected WourkoutDetailsPage wourkoutDetailsPage;
    protected ReportPage reportPage;
    protected CalculatorPage calculatorPage;
    protected LogoutPage logoutPage;
    protected EquipmentPage equipmentPage;
    protected ShoesPage shoesPage;
    protected String BASE_URL = PropertyReader.getProperty("url");

    @BeforeClass(alwaysRun = true)
    @Parameters({"browserName"})
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) {
        this.loginPage = new LoginPage();
        this.calendarPage = new CalendarPage();
        this.addWorkoutPage = new AddWorkoutPage();
        this.dashboardPage = new DashboardPage();
        this.wourkoutDetailsPage = new WourkoutDetailsPage();
        this.reportPage = new ReportPage();
        this.calculatorPage = new CalculatorPage();
        this.logoutPage = new LogoutPage();
        this.equipmentPage = new EquipmentPage();
        this.shoesPage = new ShoesPage();
        Configuration.baseUrl = BASE_URL;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = browser;
        Configuration.timeout = 10000;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

    }

    @BeforeMethod(onlyForGroups = "withSuccessLogin", alwaysRun = true)
    public void preConditionForGroup() {
        loginPage.login(BASE_LOGIN, BASE_PASSWORD);
    }

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        open("/");
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        closeWebDriver();
    }
}
