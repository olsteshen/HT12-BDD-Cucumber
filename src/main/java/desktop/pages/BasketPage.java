package desktop.pages;

import abstractclasses.page.AbstractPage;
import driver.SingletonDriver;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

import static constants.Constants.*;


public class BasketPage extends AbstractPage {
    @FindBy(xpath = "//input[@id='signInSubmit']")
    WebElement submitButton;
    @FindBy(xpath="//*[contains(@class,'btn')]")
    WebElement popUpButton;

    @FindBy(xpath="//div[@class='basket-totals']//dl[@class='delivery-text']/dd")
    WebElement delivery;
    @FindBy(xpath="//div[@class='basket-totals']//dl[@class='total']/dd")
    WebElement totalCost;

    @FindBy(xpath="//*[contains(@class,'checkout-btn')]")
    public WebElement checkoutButton;


    public BasketPage(WebDriver driver) {
        super(driver);
        SingletonDriver.getInstance();
    }

    public void checkBasketPageURL(){
    //    return getPageUrl().equals(BASKET_PAGE_URL);
        Assertions.assertEquals(BASKET_PAGE_URL, SingletonDriver.getInstance().getCurrentUrl(), "Wrong Basket page url");
    }


    public CheckoutPage buttonCheckoutOnBasket(){
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public void checkBasketOrderSummary(DataTable orderSummary){
        List<Map<String,String>> data = orderSummary.asMaps(String.class, String.class);
        Assertions.assertAll("Error on basket",
                () -> Assertions.assertEquals(data.get(0).get("Delivery cost"), delivery.getText()),
                () -> Assertions.assertEquals(data.get(0).get("Total"), totalCost.getText())
        );
    }
}
