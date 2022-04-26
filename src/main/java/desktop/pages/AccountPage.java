package desktop.pages;

import abstractclasses.page.AbstractPage;
import desktop.fragments.NavigationBarFragment;
import driver.SingletonDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static constants.Constants.SIGN_IN_PAGE_URL;


public class AccountPage extends AbstractPage {
    @FindBy(xpath = "//h1[contains(@class,'a-spacing-top-small')]")
    public WebElement titleText;
    @FindBy(xpath = "//*[@id='ap_email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;
    @FindBy(xpath = "//input[@id='signInSubmit']")
    WebElement submitButton;
    NavigationBarFragment navigationBar = new NavigationBarFragment(driver);

    public AccountPage(WebDriver driver) {
        super(driver);
        SingletonDriver.getInstance();
    }

    public void checkURL() {
        Assertions.assertEquals(SIGN_IN_PAGE_URL, SingletonDriver.getInstance().getCurrentUrl(), "Wrong page url" );
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        submitButton.click();
    }

    public Boolean isLoginTitleDisplayed() {
        driver.switchTo().frame(0);
        return titleText.isDisplayed();
    }

    public void fillInSignInFields(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }
}