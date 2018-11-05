package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {



    private WebDriver webDriver;

    private String searchTerm="HR";

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[contains(@aria-owns, 'results')]")
    private WebElement searchField;

    @FindBy(xpath = "//*[@data-control-name=\"nav.settings_signout\"]")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"nav-settings__dropdown-trigger\"]/div/span[2]/li-icon")
    private WebElement openMenuDarButton;





    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    public boolean isprofileNavItemDisplayed(){
        return profileNavItem.isDisplayed();

    }

    public SearchPage search(String searchTerm)  {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchPage(webDriver);
    }

    public LoginPage logout(){
        openMenuDarButton.click();
        logoutButton.click();
        return new LoginPage(webDriver);



    }

}
