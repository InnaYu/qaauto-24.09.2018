package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage{

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


    /**
     * @param webDriver
     * initialising the elements in LoginSubmitPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     */
    public LoginSubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the SignInButton and allertbox is displayed
     */
    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isSignInButtonDisplayed()
                && alertBox.isDisplayed();
    }

    /**
     * @return String with text of emailValidationMessage
     */
    public String getEmailValidationMessage() {

        return emailValidationMessage.getText();
    }


    /**
     * @return String with text of passwordValidationMessage
     */
    public String getPasswordValidationMessage(){
        return passwordValidationMessage.getText();

    }

    /**
     * @return true if signInButton is Displayed
     */
    public boolean isSignInButtonDisplayed(){
        waitUntilElementIsVisible(signInButton,5);
        return signInButton.isDisplayed();

    }


    /**
     * @return String with text of alertBox
     */
    public String getAlertMessageText() {
       return alertBox.getText();
    }



}

