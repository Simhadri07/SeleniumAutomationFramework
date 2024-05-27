import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.enumFactory;

public class DriverFactory {
   public static WebDriver driver ;

    public static WebDriver getDriver(enumFactory.EBrowserName browser) {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions options = new ChromeOptions();
//                options.setBinary("/usr/bin/google-chrome");
//                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-debugging-port=9222");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage"); //Linux
                options.addArguments("--disable-browser-side-navigation");
                options.addArguments("--disable-web-security");
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
}