package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.SearchPage;

import java.util.List;

import static java.lang.Thread.sleep;

public class SearchTest extends BaseTest{

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
    @Test
    public void basicSearchTest() {
        String searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        HomePage homePage = loginPage.login("innatestauto@gmail.com", "DgL-ce3-9mm-TKE");
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");


        SearchPage searchPage = homePage.search(searchTerm);


        Assert.assertTrue(searchPage.isPageLoaded(),"Search Page page is not loaded.");


        Assert.assertEquals(searchPage.getSearchResultsCount(), 10,
                "SearchResults count is wrong");


        Assert.assertTrue(searchPage.getSearchResults().contains(searchTerm),
                "SearchTerm was not found");

        List<String> searchResultList = searchPage.getSearchResults();

        for (String searchResult : searchResultList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm" + searchTerm+"not found");

    }
    }
}
