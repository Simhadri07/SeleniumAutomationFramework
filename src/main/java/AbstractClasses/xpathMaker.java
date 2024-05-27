package AbstractClasses;

import Interfaces.IXpathMaker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class xpathMaker implements IXpathMaker {

    private final WebDriver driver;

    public xpathMaker(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * @param ID
     * @return
     */
    @Override
    public By LocatorBy_ID(String ID){
        return By.id(ID);
    }

    /**
     * @param Name
     * @return
     */
    @Override
    public By LocatorBy_Name(String Name) {
        return By.name(Name);
    }

    /**
     * @param Xpath
     * @return
     */
    @Override
    public By LocatorBy_Xpath(String Xpath) {
        return By.xpath(Xpath);
    }

    /**
     * @param CssSelector
     * @return
     */
    @Override
    public By LocatorBy_CssSelector(String CssSelector) {
        return By.cssSelector(CssSelector);
    }

    /**
     * @param ClassName
     * @return
     */
    @Override
    public By LocatorBy_ClassName(String ClassName) {
       return By.className(ClassName);
    }

    /**
     * @param VisibleText
     * @return
     */
    @Override
    public By LocatorBy_VisibleText(String VisibleText) {
        return By.xpath(String.format(".//*[text()='%s']", VisibleText));
    }
}
