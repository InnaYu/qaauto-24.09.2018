import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement searchEmailField;


    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }



    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
                && webDriver.getTitle().contains("Reset Password | LinkedIn")
                && isResetPasswordButtonDisplayed();
    }

    //Reset Password | LinkedIn   Изменить пароль | LinkedIn

    public boolean isResetPasswordButtonDisplayed(){
        return resetPasswordButton.isDisplayed();
    }

    public RequestPasswordSubmitPage resetPasword(String userEmail) {
        searchEmailField.sendKeys(userEmail);
        resetPasswordButton.click();
        return new RequestPasswordSubmitPage(webDriver);

    }


}
