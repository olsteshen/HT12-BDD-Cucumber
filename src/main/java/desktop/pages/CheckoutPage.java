package desktop.pages;

import abstractclasses.page.AbstractPage;
import data.Address;
import driver.SingletonDriver;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static constants.Constants.*;

public class CheckoutPage extends AbstractPage {
    @FindBy(xpath = "//div[@id='deliveryAddress']//div[@class='error-block']")
    public List<WebElement> deliveryAddressErrorBlocks;
    @FindBy(xpath = "//div[@class='buynow-error-msg']")
    public WebElement paymentErrorBlocks;
    @FindBy(xpath="//input[@name='emailAddress']")
    public WebElement emailAddress;
    @FindBy(xpath="//input[@name='delivery-fullName']")
    public WebElement fullName;
    @FindBy(xpath="//select[@name='deliveryCountry']")
    public WebElement deliveryCountry;
    @FindBy(xpath="//input[@name='delivery-addressLine1']")
    public WebElement addressLine1;
    @FindBy(xpath="//input[@name='delivery-addressLine2']")
    public WebElement addressLine2;
    @FindBy(xpath="//input[@name='delivery-city']")
    public WebElement city;
    @FindBy(xpath="//input[@name='delivery-county']")
    public WebElement state;
    @FindBy(xpath="//input[@name='delivery-postCode']")
    public WebElement postcode;
    @FindBy(xpath="//input[@name='cvv']")
    public WebElement cvv;
    @FindBy(xpath = "//input[@name='expiration']")
    public WebElement expiration;
    @FindBy(xpath = "//input[@name='credit-card-number']")
    public WebElement ccNumber;
    @FindBy(xpath = "//div[@class='wrapper']/dl[1]/dd")
    public WebElement subTotal;
    @FindBy(xpath = "//div[@class='wrapper']/dl[2]/dd")
    public WebElement delivery;
    @FindBy(xpath = "//dd[@class='text-right total-tax']")
    public WebElement vatTax;
    @FindBy(xpath = "//dd[@class='text-right total-price']")
    public WebElement totalPrice;
    @FindBy(xpath="//button[@type='submit']")
    public WebElement buyNowButton;
    @FindBy(xpath="//div[@id='emailAddress']//div[@class='error-block']")
    public WebElement emailAddressError;
    @FindBy(xpath="//div[@id='delivery-fullName']//div[@class='error-block']")
    public WebElement fullNameError;
    @FindBy(xpath="//div[@id='delivery-addressLine1']//div[@class='error-block']")
    public WebElement addressLine1Error;
    @FindBy(xpath="//div[@id='delivery-city']//div[@class='error-block']")
    public WebElement cityError;
    @FindBy(xpath="//div[@id='delivery-postCode']//div[@class='error-block']")
    public WebElement postcodeError;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        SingletonDriver.getInstance();
    }


    public void checkCheckoutPageURL(){
        Assertions.assertEquals(CHECKOUT_PAGE_URL, SingletonDriver.getInstance().getCurrentUrl(), "Wrong Checkout page url" );
    }

    public WebElement buyNowButton(){
        return buyNowButton;
    }

    public List<WebElement> getErrorMessageAddressForm(){
            return deliveryAddressErrorBlocks;
        }

     public WebElement getErrorMessagePaymentForm(){
        return paymentErrorBlocks;
     }

    public void fillInUserEmail(String email){
        emailAddress.sendKeys(email);
    }

    public void fillAddressFields(DataTable deliveryAddress){
        Select countrySelect = new Select(deliveryCountry);
        List<Map<String,String>> data= deliveryAddress.asMaps(String.class, String.class);
        fullName.sendKeys(data.get(0).get("Full name"));
        countrySelect.selectByVisibleText(data.get(0).get("Delivery country"));
        addressLine1.sendKeys(data.get(0).get("Address line 1"));
        addressLine2.sendKeys(data.get(0).get("Address line 2"));
        city.sendKeys(data.get(0).get("Town/City"));
        state.sendKeys(data.get(0).get("County/State"));
        postcode.sendKeys(data.get(0).get("Postcode"));

//        for(Address address: deliveryAddress){
//            fullName.sendKeys(address.getFullName());
//            countrySelect.selectByVisibleText(address.getCountry());
//            addressLine1.sendKeys(address.getLine1());
//            addressLine2.sendKeys(address.getLine2());
//            city.sendKeys(address.getCity());
//            state.sendKeys(address.getState());
//            postcode.sendKeys(address.getPostcode());
//        }
    }

    public void enterCardDetails(DataTable cardDetails){
        Map<String,String> data = cardDetails.asMap(String.class, String.class);
        ccNumber.sendKeys(data.get("cardNumber"));
        expiration.sendKeys(data.get("Expiry date"));
        cvv.sendKeys(data.get("Cvv"));
    }

    public void checkOrderSummary(DataTable orderDetails){
        List<Map<String,String>> data = orderDetails.asMaps(String.class, String.class);
        Assertions.assertAll("Error on checkout",
                () -> Assertions.assertEquals(data.get(0).get("Sub-total"), subTotal.getText()),
                () -> Assertions.assertEquals(data.get(0).get("Delivery"), delivery.getText()),
                () -> Assertions.assertEquals(data.get(0).get("VAT"), vatTax.getText()),
                () -> Assertions.assertEquals(data.get(0).get("Total"), totalPrice.getText()));
    }

    public void checkErrorMessage(DataTable expectedErrors){
        Map<String, String> data= expectedErrors.asMap(String.class, String.class);
        Assertions.assertAll("Error on checkout",
                () -> Assertions.assertEquals(data.get("Email address"), emailAddressError.getText()),
                () -> Assertions.assertEquals(data.get("Full name"), fullNameError.getText()),
                () -> Assertions.assertEquals(data.get("Address line 1"), addressLine1Error.getText()),
                () -> Assertions.assertEquals(data.get("Town/City"), cityError.getText()),
                () -> Assertions.assertEquals(data.get("Postcode/ZIP"), postcodeError.getText())
                );
    }
}
