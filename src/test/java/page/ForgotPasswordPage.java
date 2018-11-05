package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

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



    public boolean isResetPasswordButtonDisplayed(){
        return resetPasswordButton.isDisplayed();
    }

    public RequestPasswordSubmitPage resetPasword(String userEmail) {

        GMailService gMailService = new GMailService();
        gMailService.connect();

        searchEmailField.sendKeys(userEmail);
        resetPasswordButton.click();

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "security-noreply@linkedin.com";
        String messageFrom = "innatestauto@gmail.com";


        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);



        return new RequestPasswordSubmitPage(webDriver);

    }


}
