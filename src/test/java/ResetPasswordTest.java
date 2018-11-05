import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ResetPasswordTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage= new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }



    /**

     *      PreConditions:
     *   - Open new Browser.
     *   - Navigate to linkedin.com
     *
     *      Scenario:
     *  - Verify that login page is loaded.
     *  - Click on forgotPasswordPassword Button.
     *  - Verify that ForgotPasswordPage is loaded.
     *  - Enter email into ForgotPasswordPage and click forgotPasswordButton .
     *  - Verify that checkpointPage is loaded;
     *  - Verify that the ResetPasswordLetter came from the link ;
     *  - Input ResetPassword Link into browser.
     *  - Verify that ResetPasswordLinkPage is loaded.
     *  - Enter new Password into newPasswordFields and press RUTURN key.
     *  - Verify that SuccessfullRessetPasswordPage is loaded.
     *  - Log in linkedin.com with new password
     *
     *
     *   PostConditions:
     *   -Close Browser.
     *
     *      */

    @Test
    public void successfullResetPasswordTest() throws InterruptedException {

        String userEmail = "innatestauto@gmail.com";
        String newPassword = "La=La-Lend";

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");


        ForgotPasswordPage forgotPasswordPage = loginPage.clickOnForgotPasswordButton();
        sleep(3000);
        Assert.assertTrue(forgotPasswordPage.isPageLoaded(),
                "Forgot Password Page page is not loaded.");


        RequestPasswordSubmitPage requestPasswordSubmitPage = forgotPasswordPage.resetPasword(userEmail);
        Assert.assertTrue(requestPasswordSubmitPage.isPageLoaded(),
                "Checkpoint Page page is not loaded.");


        sleep(80000);
        ResetPasswordPage resetPasswordPage = requestPasswordSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(),
                "Reset Password Page page is not loaded.");


        SuccessfullRessetPasswordPage successfullRessetPasswordPage = resetPasswordPage.enterNewPassword(newPassword);
        Assert.assertTrue(successfullRessetPasswordPage.isPageLoaded(),
                "Reset Password Page page is not loaded.");

        HomePage homePage =successfullRessetPasswordPage.backToLinkedIn();
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");


        sleep(3000);
        LoginPage loginPage=homePage.logout();

        sleep(6000);
        loginPage.login(userEmail,newPassword);
        sleep(3000);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");
























    }
}
