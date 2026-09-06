import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.BrowserConfig;
import utilities.DriverManager;
import utilities.JSONUtils;
import utilities.enumFactory;

public class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    public static WebDriver driver;

    public static WebDriver getDriver(enumFactory.EBrowserName browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser must not be null");
        }

        boolean headless = BrowserConfig.isHeadless();
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                addCommonOptions(chromeOptions, headless);
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                addCommonOptions(edgeOptions, headless);
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        DriverManager.setDriver(driver);
        log.info("Created {} WebDriver (headless={})", browser, headless);
        return driver;
    }

    private static void addCommonOptions(
            org.openqa.selenium.chromium.ChromiumOptions<?> options, boolean headless) {
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-infobars");
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
    }
    
    public void LaunchURL(enumFactory.EWebsiteName eWebsiteURL){
        String URL = JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", eWebsiteURL.toString());
        driver.get(URL);
        log.info("Launched URL: {}", URL);
    }

    public void LaunchURL(String URL){
        if (URL == null || URL.trim().isEmpty()) {
            throw new IllegalArgumentException("URL must not be blank");
        }
        driver.get(URL);
        log.info("Launched URL: {}", URL);
    }

    public void NavigateToURL(enumFactory.EWebsiteName eWebsiteURL){
        String URL = JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", eWebsiteURL.toString());
        driver.navigate().to(URL);
        log.info("Navigating to URL: {}", URL);
    }

    public void NavigateToURL(String URL){
        driver.navigate().to(URL);
        log.info("Navigating to URL: {}", URL);
    }
}
