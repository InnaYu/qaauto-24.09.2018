import org.openqa.selenium.By;
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


    public LoginSubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().equals("Войти в LinkedIn")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();

    }


}