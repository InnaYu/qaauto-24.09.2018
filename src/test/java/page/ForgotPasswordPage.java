package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;


public class ForgotPasswordPage extends BasePage {

    private WebDriver webDriver;


    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement searchEmailField;


    /**
     * @param webDriver
     * initialising the elements within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     */
    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    /**
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the resetPasswordButton is displayed
     */
    public boolean isPageLoaded() {

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
                && webDriver.getTitle().contains("Reset Password | LinkedIn")
                && isResetPasswordButtonDisplayed();
    }


    /**
     * @return true if resetPasswordButton is Displayed
     */
    public boolean isResetPasswordButtonDisplayed(){

        waitUntilElementIsVisible(resetPasswordButton, 5);
        return resetPasswordButton.isDisplayed();
    }


    /**
     * @param userEmail
     * @return RequestPasswordSubmitPage after connecting to the  gMailService, entering userEmail and clicking on resetPasswordButton
     */
    public RequestPasswordSubmitPage findAccount(String userEmail) {
        gMailService=new GMailService();
        gMailService.connect();
        searchEmailField.sendKeys(userEmail);
        resetPasswordButton.click();

        return new RequestPasswordSubmitPage(webDriver);

    }



}
