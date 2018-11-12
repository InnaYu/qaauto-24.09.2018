package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage{


  //  private   WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"login-email\"]")
    private WebElement userEmailField ;

    @FindBy(xpath = "//*[@id=\"login-password\"]")
    private WebElement userPasswordField ;

    @FindBy(xpath = "//*[@id=\"login-submit\"]")
    private WebElement signInButton ;

    @FindBy(xpath = "//*[@class=\"link-forgot-password\"]")
    private WebElement forgotPasswordButton ;

    /**
     * initialising the elements in LoginPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     * @param webDriver
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     *
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the login button is displayed
     */
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    /** true if signInButton is Displayed
     * @return true if signInButton is Displayed
     */
    public boolean isSignInButtonDisplayed(){
       waitUntilElementIsVisible(signInButton,10);
        return signInButton.isDisplayed();

    }

    /**
     * @return ForgotPasswordPage  after clicking on the Forgot Password Button, if the button is not  displayed, waiting
     */
    public ForgotPasswordPage clickOnForgotPasswordButton(){
        waitUntilElementIsClickable(forgotPasswordButton);
        forgotPasswordButton.click();
        return new ForgotPasswordPage(webDriver);
    }


    /**
     * Method that logs in with specific credentials.
     * @param userEmail - String with userEmail;
     * @param userPassword - String with userPassword;
     * @param <T> - Generic type to cast different Page Objects.
     * @return  Either home page
     *
     */
    public <T> T login(String userEmail, String userPassword)  {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        if (isUrlContains("/feed", 5)){

            return (T) new HomePage(webDriver);
        }
        if (isUrlContains("/uas/login-submit",5)) {
            return (T) new LoginSubmitPage(webDriver);
        }else {

            return (T) new LoginPage(webDriver);
        }


    }


}

