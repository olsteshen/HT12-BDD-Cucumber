package abstractclasses.page;

import driver.SingletonDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static constants.Constants.BOOK_ITEM_TITLE;
import static java.lang.String.format;

public abstract class AbstractPage {
    protected WebDriver driver;
    private String pageUrl;
    private String pageUrlPattern;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return SingletonDriver.getInstance().getCurrentUrl();
    }

    public String setPageUrlPattern(String pageUrlPattern) {
        return this.pageUrlPattern = pageUrlPattern;
    }

    public String getPageUrlPattern() {
        return pageUrlPattern;
    }

    public WebElement getProductTile(String name){
        return driver.findElement(By.xpath(format(BOOK_ITEM_TITLE, name)));
    }

}