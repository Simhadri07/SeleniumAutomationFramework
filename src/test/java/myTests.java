import org.testng.annotations.Test;

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
}
