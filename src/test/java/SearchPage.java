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

public class SearchPage {


    ;
    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class, 'search-result search-result__occluded-item')]")
    private List<WebElement> searchResultList;

    @FindBy(xpath = "//div[contains(@class,'search-filters-bar')]")
    private WebElement searchBar;




    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("/search/results")
                && webDriver.getTitle().contains("\"HR\" | Поиск | LinkedIn")
                && searchBar.isDisplayed();
    }

    public boolean isDisplayedSearchBar() {
        return searchBar.isDisplayed();

    }


    public int getSearchResultsCount() {
        return searchResultList.size();
    }


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