package abstractclasses.fragment;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractFragment {

    private WebElement rootElement;
    protected WebDriver driver;

    public AbstractFragment (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getRootElement () {
        return rootElement;
    }

    public void setRootElement (WebElement element) {
        this.rootElement = element;
    }

    public void waitForElement (WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void jsClicker(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void jsHighlightBookItem(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementsByClassName('book-item')[0].style.background = 'crimson'");
    }
}
