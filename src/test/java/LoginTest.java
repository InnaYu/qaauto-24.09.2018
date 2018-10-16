import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {


    /**
     * Preconditions:
     * - Open FF browser.
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com;
     * - Verify that login page is loaded;
     * - Enter userEmail into userEmail field;
     * - Enter userPassword into userPassword field;
     * - Click on signIn button;
     * - Verify that Home Page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfullLoginTest(){
        String validLogin = "innatestauto@gmail.com";
        String validPassword = "DgL-ce3-9mm-TKE";
        String loginXpath = "//*[@id=\"login-email\"]";
        String passwordXpath = "//*[@id=\"login-password\"]";
        String signInButtonXpath = "//*[@id=\"login-submit\"]";


        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/", "Home page URL is wrong");

        WebElement login = webDriver.findElement(By.xpath(loginXpath));
        login.sendKeys(validLogin);

        WebElement password = webDriver.findElement(By.xpath(passwordXpath));
        password.sendKeys(validPassword);

        WebElement signInButton = webDriver.findElement(By.xpath(signInButtonXpath));
        signInButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/feed/", "Account page URL is wrong");

        webDriver.quit();

    }
}
