package desktop.fragments;

import abstractclasses.fragment.AbstractFragment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBarFragment extends AbstractFragment {

    @FindBy(xpath = "//div[@class='user-nav-wrap ']")
    public WebElement navigationBar;

    @FindBy(xpath = "//li[@class='mob-nav-account']/a[contains(@href,'/login')]")
    public WebElement signInLabel;

    public NavigationBarFragment(WebDriver driver){
        super(driver);
    }

    public void signInLabelClick(){
        signInLabel.click();
    }

    public boolean checkNavigationBarDisplayed(){
        return navigationBar.isDisplayed();
    }
}