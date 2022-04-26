package desktop.pages;

import abstractclasses.page.AbstractPage;
import desktop.fragments.NavigationBarFragment;
import driver.SingletonDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static constants.Constants.HOME_PAGE_URL;

public class HomePage extends AbstractPage {
    NavigationBarFragment navigationBar = new NavigationBarFragment(driver);
    @FindBy(xpath = "//input[@name='searchTerm']")
    private WebElement searchBarInput;
    @FindBy(xpath = "//button[@class='header-search-btn']")
    private WebElement searchButton;
    @FindBy(xpath = "//div[@class='secondary-header']")
    private WebElement navigationCategories;

    public HomePage(WebDriver driver) {
        super(driver);
        SingletonDriver.getInstance().get(HOME_PAGE_URL);
    }

    public void enterSearchTerm(String searchTerm) {
        searchBarInput.sendKeys(searchTerm);
    }

    public SearchResultsPage searchButtonClick() {
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public AccountPage navBarClick() {
        navigationBar.signInLabelClick();
        return new AccountPage(driver);
    }

}