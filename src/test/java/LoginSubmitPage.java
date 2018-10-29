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
    private WebElement generalAlertMessage;

    @FindBy(xpath ="//*[@id=\"session_key-login-error\"]")
    private WebElement emailValidationMessage;

    @FindBy(xpath ="//*[@id=\"session_password-login-error\"]")
    private WebElement passwordValidationMessage;




    public LoginSubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isSignInButtonDisplayed()
                && isGeneralAlertMessage();
    }



    public String errorMessageForEmail(){
        return emailValidationMessage.getText();

    }

    public String errorMessageForPassword(){
        return passwordValidationMessage.getText();

    }

    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();

    }

    public boolean isGeneralAlertMessage(){
        return generalAlertMessage.isDisplayed();

    };




}

