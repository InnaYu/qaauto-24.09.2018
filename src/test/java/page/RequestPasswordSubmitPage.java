package page;

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


    public RequestPasswordSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains("Please check your mail for reset password link.  | LinkedIn")
         && isVerifyMessageDisplayed();
    }

    public boolean isVerifyMessageDisplayed() {
        return confirmMessage.isDisplayed();
    }

    public ResetPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "Inna, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "innatestauto@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        Pattern pattern = Pattern.compile("Чтобы изменить пароль в LinkedIn, нажмите <a href=\"([[^\"].]{0,})\"");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            String Linkedinlink = matcher.group(1);
            Linkedinlink = Linkedinlink.replaceAll("&amp;", "&");
            System.out.println("Correct link is :" + Linkedinlink);
            webDriver.navigate().to(Linkedinlink);
        }

            return new ResetPasswordPage(webDriver);
        }
    }





















