package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordSubmitPage {

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;



    private WebDriver webDriver;

    public RequestPasswordSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
              && webDriver.getTitle().contains("Please check your mail for reset password link.  | LinkedIn")
                && isVerifyMessageDisplayed();
    }

    public boolean isVerifyMessageDisplayed(){
        return confirmMessage.isDisplayed();
    }

    public ResetPasswordPage navigateToLinkFromEmail(){
        webDriver.getCurrentUrl();
        return new  ResetPasswordPage (webDriver);


    }








}
