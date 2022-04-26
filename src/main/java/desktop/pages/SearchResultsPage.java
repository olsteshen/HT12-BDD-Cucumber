package desktop.pages;

import abstractclasses.page.AbstractPage;
import driver.SingletonDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {
    @FindBy(xpath = "//*[@class='book-item']")
    private WebElement searchResults;

    public SearchResultsPage(WebDriver driver){
        super(driver);
        SingletonDriver.getInstance();
    }

    public boolean isSearchResultsPresent() {
        return searchResults.isDisplayed();
    }
}