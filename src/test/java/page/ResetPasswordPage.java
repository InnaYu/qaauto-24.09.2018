package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends BasePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;


    @FindBy(xpath = "//*[@id=\"newPassword\"]")
    private WebElement newPasswordField;

    @FindBy(xpath = "//*[@id=\"confirmPassword\"]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;


    /**
     * @param webDriver
     * initialising the elements in ResetPasswordPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     *
     *  */
    public ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return the true if all conditions are the same:Title contains the transmitted value and the confirmMessage is displayed
     *
     */
    public boolean isPageLoaded(){
        return webDriver.getTitle().contains("Reset Your Password | LinkedIn")
                && isconfirmMessageDisplayed();
    }

    /**
     * @return true if confirmMessage is Displayed
     */
    public boolean isconfirmMessageDisplayed(){
        waitUntilElementIsVisible(confirmMessage,5);

        return confirmMessage.isDisplayed();
    }

    /**
     * @param newPassword
     * @return SuccessfullRessetPasswordPage after entering new Password in PasswordFields and clicking resetPasswordButton
     */
    public SuccessfullRessetPasswordPage enterNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(newPassword);
        resetPasswordButton.click();
        return new SuccessfullRessetPasswordPage(webDriver);



    }





}
