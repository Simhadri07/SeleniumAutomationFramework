import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import utilities.JSONUtils;
import utilities.enumFactory;
import utilities.propsUtils;

public class LoginPage {
    private WebDriver driver;

    @FindBy
    private By usernameInput = By.id("username");


    private By passwordInput = By.id("password");


    private By loginButton = By.xpath("//*[text()='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void LaunchURL(enumFactory.EWebsiteName centerName){
        String URL = JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", centerName.toString());
        driver.get(URL);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void LoginFunction(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(propsUtils.getUsername());
        loginPage.enterPassword(propsUtils.getPassword());
        loginPage.clickLoginButton();
    }
}
