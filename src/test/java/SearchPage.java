import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {


    private WebDriver webDriver;

    @FindBy(xpath = "//*[@class=\"search-result__wrapper\"]")
    public List<WebElement> searchResults;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/search/results/all/?keywords=HR&origin=GLOBAL_SEARCH_HEADER");
    }


    public int isDisplayedSearchResults() {
        return searchResults.size();
    }

    public boolean foundSearchTerm(String searchTerm) {
        HomePage homePage = new HomePage(webDriver);


        for (WebElement searchResult : searchResults) {


            String searchResultText = searchResult.getText();

            if (searchResultText.toLowerCase().contains(homePage.searchTerm.toLowerCase())) {
                return true;
            }

        }
        return false;
    }
}