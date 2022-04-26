package abstractclasses.fragment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractFragment {

    private WebElement rootElement;
    protected WebDriver driver;

    public AbstractFragment (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    };

    public WebElement getRootElement() {
        return rootElement;
    }

    public void setRootElement(WebElement element) {
        this.rootElement = element;
    }
}
