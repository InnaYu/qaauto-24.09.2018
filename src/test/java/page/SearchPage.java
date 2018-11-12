package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Thread.sleep;

public class SearchPage  extends BasePage{



    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class, 'search-result search-result__occluded-item')]")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//div[contains(@class,'search-filters-bar')]")
    private WebElement searchBar;


    /**
     * @param webDriver
     * initialising the elements in SearchPage within the constructor of the PageObject (PO) by taking advantage of the “this” keyword to refer to the current class instance
     *      *
     */
    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    /**
     * @return the true if all conditions are the same: the current link contains the transmitted value, Title contains the transmitted value and the searchBar is displayed
     *
     */
    public boolean isPageLoaded(){
        waitUntilElementIsClickable(searchBar);
        return webDriver.getCurrentUrl().contains("/search/results")
                && webDriver.getTitle().contains("\"HR\" | Поиск | LinkedIn")
                && isDisplayedSearchBar();
    }

    /**
     * @return true if searchBar is Displayed
     */
    public boolean isDisplayedSearchBar() {
        return searchBar.isDisplayed();

    }


    /**
     * @return count of visible SearchResults
     */
    public int getSearchResultsCount() {

        return searchResultList.size();
    }


    /**
     * @return searchResultStringList wich contains SearchTerm and scrolling page down
     */
    public List getSearchResults() {
        List<String> searchResultStringList = new ArrayList<String>();

       for (WebElement searchResult: searchResultList){

           ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", searchResult);

           String searchResultText= searchResult.getText();
           searchResultStringList.add(searchResultText);
       }
        return searchResultStringList;

    }


}