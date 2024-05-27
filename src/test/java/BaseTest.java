import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.RandomUtils;
import utilities.LogManagerUtils;
import utilities.ScreenshotMaker;
import utilities.enumFactory;

public class BaseTest extends DriverFactory{

    LogManagerUtils logManagerUtils = new LogManagerUtils();

    @BeforeMethod
    public void BrowserSetup() {
        LogManagerUtils.clearLogs();
        ScreenshotMaker.clearScreenshots();
            driver = new DriverFactory().getDriver(enumFactory.EBrowserName.CHROME);
        driver.manage().window().maximize();
        LogManagerUtils.configure(false);
       new DriverFactory().LaunchURL(enumFactory.EWebsiteName.demoURL);
    }

    @AfterMethod
    public void tearDown() {
        RandomUtils randomUtils = new RandomUtils();
        ScreenshotMaker.takeScreenshot(driver,  randomUtils.generateRandom_AlphaNumeric_String(10)+ "_screenshot");
        driver.close();
        driver.quit();
        System.out.println("<<< *** T H E  E N D *** >>>");
    }

}
