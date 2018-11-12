package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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


    /**
     * @param webDriver
     * initialising the elements within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the profileNavItem is displayed
     */
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isprofileNavItemDisplayed();
    }

    /**
     * @return true if profileNavItem is Displayed
     */
    public boolean isprofileNavItemDisplayed(){
        waitUntilElementIsVisible(profileNavItem,5);
        return profileNavItem.isDisplayed();

    }

    /**
     * @param searchTerm
     * @return SearchPage after entering searchTerm in searchField and clicking enter
     */
    public SearchPage search(String searchTerm)  {
        waitUntilElementIsClickable(searchField);
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        return new SearchPage(webDriver);
    }

    /**
     * @return LoginPage after logout
     */
    public LoginPage logout(){
        openMenuDarButton.click();
        logoutButton.click();
        return new LoginPage(webDriver);



    }

}
