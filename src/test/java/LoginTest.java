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


        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/", "Home page URL is wrong");


        WebElement login = webDriver.findElement(By.xpath("//*[@id=\"login-email\"]"));
        login.sendKeys(validLogin);

        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"login-password\"]"));
        password.sendKeys(validPassword);

        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id=\"login-submit\"]"));
        signInButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/feed/", "Account page URL is wrong");


        webDriver.quit();

    }
}
