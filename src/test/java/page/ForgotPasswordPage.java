package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class ForgotPasswordPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement searchEmailField;


    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }



    public boolean isPageLoaded(){


        return// webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
             //   && webDriver.getTitle().contains("Reset Password | LinkedIn")
              //  &&
        isResetPasswordButtonDisplayed();
    }



    public boolean isResetPasswordButtonDisplayed(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resetPasswordButton.isDisplayed();
    }

    public RequestPasswordSubmitPage findAccount(String userEmail) {

        GMailService gMailService = new GMailService();
        gMailService.connect();

        searchEmailField.sendKeys(userEmail);
        resetPasswordButton.click();

        String messageSubject = "Inna, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "innatestauto@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";


        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        Pattern p = Pattern.compile("Чтобы изменить пароль в LinkedIn, нажмите <a href=\"([[^\"].]{0,})\"",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
        Matcher m = p.matcher(message);
        if (m.find()) {
            String Linkedinlink = m.group(1);
            Linkedinlink = Linkedinlink.replaceAll("&amp;", "&");
            System.out.println("Correct link is :" + Linkedinlink);
            webDriver.navigate().to(Linkedinlink);

        }

        return new RequestPasswordSubmitPage(webDriver);

    }


}
