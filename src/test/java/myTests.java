import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.enumFactory;

public class myTests extends  BaseTest {

    @Test
    public void test1() {

        System.out.println("test case 1");
        driver.navigate().to("https://www.google.com");
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
