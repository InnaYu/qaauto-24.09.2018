package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest{

    /**

     *      PreConditions:
     *   - Open new Browser.
     *   - Navigate to linkedin.com
     *
     *      Scenario:
     *  - Verify that login page is loaded.
     *  - Click on forgotPasswordPassword Button.
     *  - Verify that page.ForgotPasswordPage is loaded.
     *  - Enter email into page.ForgotPasswordPage and click forgotPasswordButton .
     *  - Verify that checkpointPage is loaded;
     *  - Verify that the ResetPasswordLetter came from the link ;
     *  - Input ResetPassword Link into browser.
     *  - Verify that ResetPasswordLinkPage is loaded.
     *  - Enter new Password into newPasswordFields and press RUTURN key.
     *  - Verify that page SuccessfullRessetPassword is loaded.
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

        Assert.assertTrue(forgotPasswordPage.isPageLoaded(),
                "Forgot Password Page page is not loaded.");


        RequestPasswordSubmitPage requestPasswordSubmitPage = forgotPasswordPage.findAccount(userEmail);

        Assert.assertTrue(requestPasswordSubmitPage.isPageLoaded(),
               "RequestPasswordSubmitPage is not loaded.");



        ResetPasswordPage resetPasswordPage = requestPasswordSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(resetPasswordPage.isPageLoaded(),
                "Reset Password Page page is not loaded.");


        SuccessfullRessetPasswordPage successfullRessetPasswordPage = resetPasswordPage.enterNewPassword(newPassword);
        Assert.assertTrue(successfullRessetPasswordPage.isPageLoaded(),
                "SuccessfullRessetPasswordPage  is not loaded.");

        HomePage homePage =successfullRessetPasswordPage.backToLinkedIn();
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");



        LoginPage loginPage=homePage.logout();


        loginPage.login(userEmail,newPassword);

        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");


    }
}
