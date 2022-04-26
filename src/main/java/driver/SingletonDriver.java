package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Optional;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;


public class SingletonDriver {
    private static WebDriver driver;
    private static final int IMPLICIT_WAIT_TIMEOUT = 5;
    private static final int PAGE_LOAD_TIMEOUT = 10;
    private final static String CHROME_DRIVER_PATH = "./src/main/resources/chromedriver";
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private SingletonDriver() {
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        WebDriverManager.chromedriver().driverVersion("100.0.4896.88 ").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            new SingletonDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        Optional.ofNullable(getInstance()).ifPresent(webDriver -> {
            webDriver.quit();
            webDriverThreadLocal.remove();
        });
    }

}