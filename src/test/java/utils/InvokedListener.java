package utils;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedListener implements IInvokedMethodListener {
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            AllureUtils.attachScreenshot(driver);
            AllureUtils.attachHtml(driver);
        }
    }
}
