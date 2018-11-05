package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;


    @FindBy(xpath = "//*[@id=\"newPassword\"]")
    private WebElement newPasswordField;

    @FindBy(xpath = "//*[@id=\"confirmPassword\"]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;



    public ResetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getTitle().contains("Reset Your Password | LinkedIn")
                && isconfirmMessageDisplayed();
    }

    public boolean isconfirmMessageDisplayed(){
        return confirmMessage.isDisplayed();
    }

    public SuccessfullRessetPasswordPage enterNewPassword(String newPassword) {
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(newPassword);
        resetPasswordButton.click();
        return new SuccessfullRessetPasswordPage(webDriver);



    }





}
