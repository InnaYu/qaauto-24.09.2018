import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class SearchTest {

    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage= new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
    /**
     * PreConditions:
     * - Open new Browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login pageis loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter 'searchTerm' into search and press RUTURN key.
     * - Verify that Search page is loaded;
     * - Verify 10 searchResults displayed on page.
     * - Verify each result item contains searchTerm
     *
     * PostConditions:
     * -Close Browser.
     */
    public void basicSearchTest() throws InterruptedException {

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        HomePage homePage = loginPage.login("innatestauto@gmail.com", "DgL-ce3-9mm-TKE");
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");

        homePage.enterSearchTerm(homePage.searchTerm);

        sleep(10000);

        SearchPage searchPage = new SearchPage(webDriver);

        Assert.assertTrue(searchPage.isPageLoaded(),"Search Page page is not loaded.");

        sleep(10000);

        Assert.assertEquals(searchPage.isDisplayedSearchResults(), 10, "Other count of search results");

        Assert.assertTrue(searchPage.foundSearchTerm(homePage.searchTerm),"SearchTerm was not found");


    }

}
