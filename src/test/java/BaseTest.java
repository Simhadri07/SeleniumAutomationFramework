import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.JavaUtils;
import utilities.LogManagerUtils;
import utilities.ScreenshotMaker;
import utilities.enumFactory;

public class BaseTest extends DriverFactory{

    @BeforeMethod
    public void BrowserSetup() {
        LogManagerUtils.clearLogs();
        ScreenshotMaker.clearScreenshots();
            driver = DriverFactory.getDriver(enumFactory.EBrowserName.CHROME);
        driver.manage().window().maximize();
        LogManagerUtils.configure(false);
       new LoginPage(driver).LaunchURL(enumFactory.EWebsiteName.demoURL);
    }

    @AfterMethod
    public void tearDown() {
        JavaUtils javaUtils = new JavaUtils();
        ScreenshotMaker.takeScreenshot(driver,  javaUtils.generateRandomString(10)+ "_screenshot");
        driver.quit();
        System.out.println("<<< *** T H E  E N D *** >>>");
    }

}
