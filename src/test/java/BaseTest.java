import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.PropertyReader;

import java.time.Duration;

//@Listeners({InvokedListener.class, TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected String BASE_URL = PropertyReader.getProperty("url");
    protected static final String BASE_LOGIN = PropertyReader.getProperty("login");
    protected static final String BASE_PASSWORD = PropertyReader.getProperty("password");

    @BeforeClass(alwaysRun = true)
    @Parameters({"browserName"})
    public void setUp(@Optional("chrome") String browser, ITestContext testContext) throws Exception {
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.loginPage = new LoginPage(driver);

    }
    @BeforeMethod(onlyForGroups = "withSuccessLogin")
    public void preConditionForGroup() {
        loginPage.login(BASE_LOGIN, BASE_PASSWORD);
    }
    @AfterMethod()
    public void postCondition() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        loginPage.open(BASE_URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.driver.quit();
    }


}
