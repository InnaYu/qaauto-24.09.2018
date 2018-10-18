import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }


    /**
     * Preconditions:
     * - Open FF browser.
     * <p>
     * Scenario:
     * - Navigate to https://www.linkedin.com;
     * - Verify that login page is loaded;
     * - Enter userEmail into userEmail field;
     * - Enter userPassword into userPassword field;
     * - Click on signIn button;
     * - Verify that Home Page is loaded.
     * <p>
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfullLoginTest() {

        webDriver.navigate().to("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");

        Assert.assertTrue(loginPage.signInButton.isDisplayed(),
                "SignInButton is not displayed on Login page");

        loginPage.login("innatestauto@gmail.com", "DgL-ce3-9mm-TKE");


        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/",
                "Home page URL is wrong");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn",
                "Home page title is wrong");

        HomePage homePage = new HomePage(webDriver);

        Assert.assertTrue(homePage.profileNavItem.isDisplayed(),
                "profileNavItem is not displayed on Login page");

    }

    @Test
    public void negativeLoginWithEmptyPasswordTest () {

        webDriver.navigate().to("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong");

        loginPage.login("a@v.c", "");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong");

    }

    @Test
    public void negtiveWrongPasswordTest () {
        webDriver.navigate().to("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong");

        loginPage.login("innatestauto@gmail.com", "laLa-omc90_99");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME",
                "Login page URL is wrong");

    }

}
