package desktop.pages;

import abstractclasses.fragment.AbstractFragment;
import abstractclasses.page.AbstractPage;
import driver.SingletonDriver;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;


public class SearchResultsPage extends AbstractPage {
    @FindBy(xpath = "//*[@class='book-item']")
    private List<WebElement> searchResults;
    @FindBy(xpath = "//h3[@class='title']")
    private List<WebElement> bookTitle;
    @FindBy(xpath="//select[@name='price']")
    private WebElement priceFilter;
    @FindBy(xpath="//select[@name='availability']")
    private WebElement availabilityFilter;
    @FindBy(xpath="//select[@name='searchLang']")
    private WebElement langFilter;
    @FindBy(xpath="//select[@name='format']")
    private WebElement formatFilter;
    @FindBy(xpath = "//div[@class='book-item']//h3/a")
    private WebElement bookItemTitle;
    @FindBy(xpath="//button[@class='btn btn-primary'][contains(text(),'Refine results')]")
    private WebElement refineResultsButton;
    @FindBy(xpath="//a[contains(@class,'continue-to-basket')]")
    public WebElement continueButton;

    AbstractFragment fragment = new AbstractFragment(driver) {
       @Override
       public void waitForElement(WebElement element) {
           super.waitForElement(element);
       }
   };

    public SearchResultsPage(WebDriver driver){
        super(driver);
        SingletonDriver.getInstance();
    }

    public boolean isSearchResultsPresent() {
        return !searchResults.isEmpty();
    }

    public String pageURL(){
       return  getPageUrl();
    }

    public List<WebElement> getBookTitleInResults(){
        return bookTitle;
    }

    public void applyFilters(DataTable filtersData){
        Map<String,String> data = filtersData.asMap(String.class, String.class);
        Select selectPriceFilter = new Select(priceFilter);
        Select selectAvailFilter = new Select(availabilityFilter);
        Select selectLangFilter = new Select(langFilter);
        Select selectFormatFilter = new Select(formatFilter);

        selectPriceFilter.selectByVisibleText(data.get("Price range"));
        selectAvailFilter.selectByVisibleText(data.get("Availability"));
        selectLangFilter.selectByVisibleText(data.get("Language"));
        selectFormatFilter.selectByVisibleText(data.get("Format"));
        Actions builder = new Actions(driver);
        Action seriesOfActions = builder
                .moveToElement(refineResultsButton)
                .click()
                .build();
        seriesOfActions.perform();
    }

    public WebElement atbButtonOnProductTile(String name){
        fragment.jsHighlightBookItem();
        return  getProductTile(name).findElement(By.xpath("//a[contains(@class,'add-to-basket')]"));
    }

    public BasketPage clickContinuebutton(){
        fragment.waitForElement(continueButton);
        fragment.jsClicker(continueButton);
        return new BasketPage(driver);
    }
}