package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import util.GMailService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestPasswordSubmitPage extends BasePage {

    private WebDriver webDriver;
    GMailService gMailService;

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;


    /**
     * @param webDriver
     * initialising the elements in RequestPasswordSubmitPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     *
     */
    public RequestPasswordSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     *
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the VerifyMessage is displayed
     *
     */
    public boolean isPageLoaded() {

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains("Please check your mail for reset password link.  | LinkedIn")
         && isVerifyMessageDisplayed();
    }

    /**
     * @return true if confirmMessage is Displayed
     */
    public boolean isVerifyMessageDisplayed() {
        waitUntilElementIsVisible(confirmMessage,5);

        return confirmMessage.isDisplayed();
    }

    /**
     * @return ResetPasswordPage after logIn in e-mail, get resetPasswordLink, replace correct email and open this link in browser
     */
    public ResetPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "Inna, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "innatestauto@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(
                message, "click <a href=\"", "\"").replace("amp;", "");
        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);


            return new ResetPasswordPage(webDriver);
        }
    }





















