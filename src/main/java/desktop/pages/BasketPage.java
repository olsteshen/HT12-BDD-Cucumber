package desktop.pages;

import abstractclasses.page.AbstractPage;
import driver.SingletonDriver;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;


public class BasketPage extends AbstractPage {
    @FindBy(xpath = "//*[contains(@class,'checkout-btn')]")
    public WebElement checkoutButton;
    @FindBy(xpath = "//div[@class='basket-totals']//dl[@class='delivery-text']/dd")
    WebElement delivery;
    @FindBy(xpath = "//div[@class='basket-totals']//dl[@class='total']/dd")
    WebElement totalCost;


    public BasketPage(WebDriver driver) {
        super(driver);
        SingletonDriver.getInstance();
    }

    public String pageURL() {
        return getPageUrl();
    }

    public CheckoutPage buttonCheckoutOnBasket() {

        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public void checkBasketOrderSummary(DataTable orderSummary) {
        List<Map<String, String>> data = orderSummary.asMaps(String.class, String.class);
        Assertions.assertAll("Basket summary is not correct",
                () -> Assertions.assertEquals(data.get(0).get("Delivery cost"), delivery.getText()),
                () -> Assertions.assertEquals(data.get(0).get("Total"), totalCost.getText())
        );
    }
}
