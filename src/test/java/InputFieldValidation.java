import AbstractClasses.xpathMaker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.RandomUtils;

public class InputFieldValidation {
    private WebDriver driver;

    xpathMaker LocatorMaker = new xpathMaker(driver);
    @FindBy
    private By inputField = By.id("text-input");
    @FindBy
    private By clearButton = LocatorMaker.LocatorBy_VisibleText("Clear");

    public InputFieldValidation(WebDriver driver) {
        this.driver = driver;
    }

    public void enterValueInField(String username) {
        driver.findElement(inputField).sendKeys(username);
    }

    public void clickClearButton() {
        driver.findElement(clearButton).click();
    }

    public void InputFieldFunction() {
        InputFieldValidation inputFieldValidation = new InputFieldValidation(driver);
        RandomUtils randomUtils = new RandomUtils();
        for (int i = 0; i <= 10; i++) {
            inputFieldValidation.enterValueInField(randomUtils.generateRandom_AlphaNumeric_String(15));
            inputFieldValidation.clickClearButton();
        }
    }
}
