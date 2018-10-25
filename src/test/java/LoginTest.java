import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

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
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");
        HomePage homePage =loginPage.login("innatestauto@gmail.com", "DgL-ce3-9mm-TKE");

        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");


    }


    @Test
    public void negativeLoginWithEmptyPasswordTest () {

        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        loginPage.loginForLoginPage("a@v.c", "");

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

    }

    @Test
    public void negativeLoginWithEmptyEmailTest(){
        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");


        loginPage.login("", "Lalala");
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");
    }
    @Test
    public void negativeLoginWithEmptyEmailAndPasswordTest(){
        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        loginPage.login("", "");
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");
    }


    @Test
    public void negativeWrongEmailTest () throws InterruptedException {
        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        LoginSubmitPage loginSubmitPage=loginPage.loginForSubmitPage("inamTestauto@gmail.com", "DgL-ce3-9mm-TKE");

        sleep(3000);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login page is not loaded");
    }



    @Test
    public void negativeWrongEmailAndPasswordTest () throws InterruptedException {
        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage=loginPage.loginForSubmitPage("inna88@gmail.com", "Df0928");

        sleep(3000);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login page is not loaded"); //https://www.linkedin.com/checkpoint/rp/request-password-reset-submit-redir?userName=AgGEDCGbSHnBIQAAAWacToM7Z1p9dWqIR7hc5SR_o815sG8aiQaLa2jPK2N95B9du6s&sid=Pwd-Reset%3Affc614bd-1c4f-40a7-a606-4351dd78bde4&ut=1KXlps8E78IEs1
    }

/*
    @Test
    public void successfullLoginAfterWrongEmailAndPasswordTest () {
        webDriver.navigate().to("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
        loginPage.login("inna88@gmail.com", "Df0928");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);


        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login page is not loaded"); //https://www.linkedin.com/checkpoint/rp/request-password-reset-submit-redir?userName=AgGEDCGbSHnBIQAAAWacToM7Z1p9dWqIR7hc5SR_o815sG8aiQaLa2jPK2N95B9du6s&sid=Pwd-Reset%3Affc614bd-1c4f-40a7-a606-4351dd78bde4&ut=1KXlps8E78IEs1

        loginSubmitPage.login("innatestauto@gmail.com", "DgL-ce3-9mm-TKE");
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }*/
/*
        @Test
        public void negativeLoginAfterWrongEmailAndPasswordTest () {
            webDriver.navigate().to("https://www.linkedin.com");
            LoginPage loginPage = new LoginPage(webDriver);
            Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
            loginPage.login("inna88@gmail.com", "Df0928");

            LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);


            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login page is not loaded"); //https://www.linkedin.com/checkpoint/rp/request-password-reset-submit-redir?userName=AgGEDCGbSHnBIQAAAWacToM7Z1p9dWqIR7hc5SR_o815sG8aiQaLa2jPK2N95B9du6s&sid=Pwd-Reset%3Affc614bd-1c4f-40a7-a606-4351dd78bde4&ut=1KXlps8E78IEs1

            loginSubmitPage.login("innatestauto@gmail.com", "90тмовKE");
            Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login page is not loaded");

        } */

}
