package desktop.pages;

import abstractclasses.page.AbstractPage;
import driver.SingletonDriver;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static constants.Constants.*;
import static java.lang.String.format;

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
    @FindBy(xpath="//div[@class='basket-page ']")
    public WebElement basketPageContent;
    @FindBy(xpath="//div[contains(@class,'modal-content')]")
    WebElement basketModal;

private static final String BOOK_ITEM_TITLE = "//div[@class='book-item']//h3/a";

    public SearchResultsPage(WebDriver driver){
        super(driver);
        SingletonDriver.getInstance();
    }

    public boolean isSearchResultsPresent() {
        return !searchResults.isEmpty();
    }

    public void checkSearchPageURL(){
        Assertions.assertTrue(SingletonDriver.getInstance().getCurrentUrl().contains(SEARCH_RESULT_PAGE_URL), "Wrong Search page url" );
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

    public WebElement atbButton(String name){
        WebElement tile = driver.findElement(By.xpath(format(BOOK_ITEM_TITLE, name)));
        return tile.findElement(By.xpath("//a[contains(@class,'add-to-basket')]"));
    }

    public BasketPage clickButtonContinue(){
//        Actions builder = new Actions(driver);
//        Action seriesOfActions = builder
//                .moveToElement(continueButton)
//                .click()
//                .build();
//        seriesOfActions.perform();
  //      continueButton.click();
       // WebElement button = driver.findElement(By.xpath("//a[contains(@class,'continue-to-basket')]"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", continueButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOf(basketPageContent));
        return new BasketPage(driver);
    }
}