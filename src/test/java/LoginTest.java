import org.openqa.selenium.WebDriver;
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

        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/", "Home page URL is wrong");




        webDriver.quit();




    }


}
