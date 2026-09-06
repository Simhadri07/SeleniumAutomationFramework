package utilities;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, IExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onExecutionStart() {
        LogManagerUtils.configure(true);
        ScreenshotMaker.clearScreenshots();
        ExtentReportManager.startReport();
        LOGGER.info("Test execution started");
    }

    @Override
    public void onExecutionFinish() {
        ExtentReportManager.flushReport();
        LOGGER.info("Test execution finished");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(
                result.getMethod().getQualifiedName(),
                result.getMethod().getDescription());
        LOGGER.info("Test started: {}", result.getMethod().getQualifiedName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentReportManager.getCurrentTest();
        if (test != null) {
            test.pass("Test passed");
        }
        LOGGER.info("Test passed: {}", result.getMethod().getQualifiedName());
        ExtentReportManager.clearCurrentTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentReportManager.getCurrentTest();
        if (test != null) {
            test.fail(result.getThrowable());
            attachFailureScreenshot(test, result);
        }
        LOGGER.error("Test failed: {}", result.getMethod().getQualifiedName(), result.getThrowable());
        ExtentReportManager.clearCurrentTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentReportManager.getCurrentTest();
        if (test != null) {
            if (result.getThrowable() == null) {
                test.skip("Test skipped");
            } else {
                test.skip(result.getThrowable());
            }
        }
        LOGGER.warn("Test skipped: {}", result.getMethod().getQualifiedName());
        ExtentReportManager.clearCurrentTest();
    }

    private void attachFailureScreenshot(ExtentTest test, ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return;
        }
        String screenshotName = result.getMethod().getMethodName() + "_failure";
        String screenshotPath = ScreenshotMaker.takeScreenshot(driver, screenshotName);
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception error) {
                LOGGER.error("Unable to attach failure screenshot to report", error);
            }
        }
    }
}
