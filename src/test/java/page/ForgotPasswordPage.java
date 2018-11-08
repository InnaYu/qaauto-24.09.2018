package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class ForgotPasswordPage extends BasePage {

    private WebDriver webDriver;


    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement searchEmailField;


    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }




    public boolean isPageLoaded() {

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
                && webDriver.getTitle().contains("Reset Password | LinkedIn")
                &&
                isResetPasswordButtonDisplayed();
    }



    public boolean isResetPasswordButtonDisplayed(){

        return resetPasswordButton.isDisplayed();
    }

    public RequestPasswordSubmitPage findAccount(String userEmail) {
        gMailService=new GMailService();

        gMailService.connect();

        searchEmailField.sendKeys(userEmail);
        resetPasswordButton.click();

        return new RequestPasswordSubmitPage(webDriver);

    }



}
