import AbstractClasses.xpathMaker;
import org.openqa.selenium.By;

public class SeleniumThings extends BaseTest {

    xpathMaker LocatorMaker = new xpathMaker(driver);

    public void clickButton(By by) {
        driver.findElement(by).click();
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
