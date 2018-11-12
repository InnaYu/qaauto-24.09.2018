package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.HomePage;

public class SuccessfullRessetPasswordPage extends BasePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/header")//переписать локатор
    private WebElement confirmMessage;

    @FindBy(xpath = "//*[@id=\"app__container\"]/header/div/div/nav/a")//переписать локатор
    private WebElement backtoLinkedInButton;


    /**
     * @param webDriver
     * initialising the elements in SuccessfullRessetPasswordPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     *
     */
    public SuccessfullRessetPasswordPage(WebDriver webDriver)  {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the СonfirmMessage is displayed
     *
     */
    public boolean isPageLoaded(){

        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
               && webDriver.getTitle().contains("You've successfully reset your password. | LinkedIn")
                && isСonfirmMessageDisplayed();
    }


    /**
     * @return true if confirmMessage is Displayed
     */
    public boolean isСonfirmMessageDisplayed(){
       waitUntilElementIsVisible(confirmMessage,10);
        return confirmMessage.isDisplayed();
    }

    /**
     * @return HomePage after clicking on backtoLinkedInButton
     */
    public HomePage backToLinkedIn(){
        backtoLinkedInButton.click();
        return new HomePage(webDriver);

    }


}
