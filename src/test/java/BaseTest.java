import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.BrowserConfig;
import utilities.DriverManager;
import utilities.LogManagerUtils;

public class BaseTest extends DriverFactory{

    @BeforeMethod
    public void browserSetup() {
        driver = getDriver(BrowserConfig.getBrowser());
        if (!BrowserConfig.isHeadless()) {
            driver.manage().window().maximize();
        }
        new DriverFactory().LaunchURL(BrowserConfig.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            DriverManager.removeDriver();
            driver = null;
        }
        LogManagerUtils.logMsg("WebDriver session closed");
    }

}
