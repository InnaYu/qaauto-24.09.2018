package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.HomePage;

public class SuccessfullRessetPasswordPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;

    @FindBy(xpath = "//*[@id=\"app__container\"]/header/div/div/nav/a")//переписать локатор
    private WebElement backtoLinkedInButton;


    public SuccessfullRessetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
               && webDriver.getTitle().contains("You've successfully reset your password. | LinkedIn")
                && isСonfirmMessageDisplayed();
    }


    public boolean isСonfirmMessageDisplayed(){
        return confirmMessage.isDisplayed();
    }

    public HomePage backToLinkedIn(){
        backtoLinkedInButton.click();
        return new HomePage(webDriver);

    }




}
