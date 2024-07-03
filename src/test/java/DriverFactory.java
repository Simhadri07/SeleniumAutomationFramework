import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.JSONUtils;
import utilities.LogManagerUtils;
import utilities.enumFactory;

public class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    public static WebDriver driver ;
    LogManagerUtils logManagerUtils = new LogManagerUtils();

    public static WebDriver getDriver(enumFactory.EBrowserName browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
//                options.setBinary("/usr/bin/google-chrome");
//                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().clearDriverCache().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }

    public void LaunchURL(enumFactory.EWebsiteName eWebsiteURL){
        String URL = JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", eWebsiteURL.toString());
        driver.get(URL);
        logManagerUtils.logMsg("Launched : "+URL);
    }

    public void LaunchURL(String URL){
        driver.get(URL);
        logManagerUtils.logMsg("Launched : "+URL);
    }

    public void NavigateToURL(enumFactory.EWebsiteName eWebsiteURL){
        String URL = JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", eWebsiteURL.toString());
        driver.navigate().to(URL);
        logManagerUtils.logMsg("Navigating to : "+URL);
    }

    public void NavigateToURL(String URL){
        driver.navigate().to(URL);
        logManagerUtils.logMsg("Navigating to : "+URL);
    }
}