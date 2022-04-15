package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Utilities.Constants;

public class BaseTest {

    public static WebDriver driver;

    @BeforeTest
    public void setUp() {
        String browser = Constants.browserName;
        if (browser != null) {
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new RuntimeException("Does not support browser + " + browser);
            }
        }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
