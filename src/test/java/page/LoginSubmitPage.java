package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {

    private WebDriver webDriver;

    @FindBy(xpath ="//*[@id=\"session_key-login\"]")
    private WebElement userEmailField ;

    @FindBy(xpath ="//*[@id=\"session_password-login\"]")
    private WebElement userPasswordField ;

    @FindBy(xpath ="//*[@id=\"btn-primary\"]")
    private WebElement signInButton ;

    @FindBy(xpath ="//*[@id=\"control_gen_1\"]")
    private WebElement alertBox;

    @FindBy(xpath ="//*[@id=\"session_key-login-error\"]")
    private WebElement emailValidationMessage;

    @FindBy(xpath ="//*[@id=\"session_password-login-error\"]")
    private WebElement passwordValidationMessage;

//div role alert
    //При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.


    public LoginSubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isSignInButtonDisplayed()
                && alertBox.isDisplayed();
    }

    public String getEmailValidationMessage() {

        return emailValidationMessage.getText();
    }


    public String getPasswordValidationMessage(){
        return passwordValidationMessage.getText();

    }

    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();

    }


    public String getAlertMessageText() {
       return alertBox.getText();
    }



}

