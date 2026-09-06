import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.enumFactory;

public class myTests extends  BaseTest {

    @Test
    public void automationDemoSiteSmokeTest() {
        new DriverFactory().NavigateToURL(enumFactory.EWebsiteName.demoURL);
        org.testng.Assert.assertTrue(
                driver.getCurrentUrl().contains("simhadri07.github.io/automationDemoSite"),
                "Automation demo site did not open");
    }

    @Test
    public void quickReferenceSiteSmokeTest() {
        new DriverFactory().NavigateToURL(enumFactory.EWebsiteName.quickRefURL);
        org.testng.Assert.assertTrue(
                driver.getCurrentUrl().contains("cheatsheets.zip"),
                "Quick reference site did not open");
    }

    @Test
    public void loginTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.LoginFunction();
    }

    @Test
    public void InputFieldTest() throws Exception {
        new DriverFactory().NavigateToURL(enumFactory.EWebsiteName.demoURL);
        InputFieldValidation inputFieldValidation = new InputFieldValidation(driver);
        inputFieldValidation.InputFieldFunction();
    }
}
