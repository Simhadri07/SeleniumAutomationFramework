import AbstractClasses.xpathMaker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumThings extends BaseTest {

    xpathMaker LocatorMaker = new xpathMaker(driver);

    public void clickButton(By by) {
        driver.findElement(by).click();
    }

    public String getText(By by) {
       return driver.findElement(by).getText();
    }
/**
 * Waits
 */

public void waitInSeconds (int seconds) throws Exception {
    synchronized (driver) {
        driver.wait(seconds*1000);
    }

}
}
