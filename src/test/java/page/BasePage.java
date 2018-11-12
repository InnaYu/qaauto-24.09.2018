package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;


public abstract class BasePage {
   public  WebDriver webDriver;
     protected static GMailService gMailService ;
     protected static String message;

    /**
     * @param webElement
     *
     * waiting that webElement will be clickable
     */
     public void waitUntilElementIsClickable(WebElement webElement){
         WebDriverWait wait=new WebDriverWait(webDriver,10);
         wait.until(ExpectedConditions.elementToBeClickable(webElement));
     }

    public WebElement waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    protected boolean isUrlContains(String partialUrl, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }

     public abstract boolean isPageLoaded();

}
