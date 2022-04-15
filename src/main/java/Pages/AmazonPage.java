package Pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AmazonPage extends BasePage {


    private static final String PRODUCTS_LINK = "(//div[@class='a-section a-spacing-small s-padding-left-small s-padding-right-small']//span[@class='a-size-base-plus a-color-base a-text-normal'])[%s]";
    private static final String PRODUCTS_PRICE = "(//div[@class='a-section a-spacing-small s-padding-left-small s-padding-right-small']//span[@class='a-price-whole'])[%s]";
    private static final String NUMBER_OF_PRODUCTS = "//div[@class='a-section a-spacing-small s-padding-left-small s-padding-right-small']//span[@class='a-price-whole']";

    @FindBy(xpath = "//a[@aria-label='Amazon']")
    private WebElement amazonLogo;

    @FindBy(xpath = "//i[@class='hm-icon nav-sprite']")
    private WebElement burgerButton;

    @FindBy(xpath = "//div[contains(text(),'TV, Appliances, Electronics')]")
    private WebElement tvAndAppliancesButton;

    @FindBy(xpath = "//a[contains(text(),'Televisions')]")
    private WebElement televisionButton;

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungCheckBox;

    @FindBy(xpath = "//span[text()='RESULTS']")
    private WebElement resultsPage;

    @FindBy(xpath = "//h1[text()=' About this item ']")
    private WebElement aboutItem;

    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageToDisplay() {
        wait.until(ExpectedConditions.visibilityOf(amazonLogo));
    }

    public void clickBurgerButton() {
        click(burgerButton);
    }

    public void clickTVAndAppliances() {
        click(tvAndAppliancesButton);
    }

    public void clickTelevisionButton() {
        click(televisionButton);
        click(samsungCheckBox);
    }

    public void clickSecondHighestProduct() {
        wait.until(ExpectedConditions.visibilityOf(resultsPage));
        Assert.isTrue(resultsPage.isDisplayed(), "Result page is not displayed.");
        Map<Integer, String> products= new TreeMap<>();
        int numberOfProductsOnPage = driver.findElements(By.xpath(NUMBER_OF_PRODUCTS)).size();
        for(int i=1; i<=numberOfProductsOnPage; i++) {
            products.put(Integer.valueOf(driver.findElement(By.xpath(String.format(PRODUCTS_PRICE, i))).getText().replace(",", "")), String.format(PRODUCTS_LINK, i));
        }
        Object val = products.values().toArray()[products.values().toArray().length - 2];
        WebElement element = driver.findElement(By.xpath(String.valueOf(val)));
        click(element);
    }

    public void switchToNewTab() {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }

    public void verifyAndPrintSpecification() {
        wait.until(ExpectedConditions.visibilityOf(aboutItem));
        Assert.isTrue(aboutItem.isDisplayed(), "About this item is not displayed.");
        List<WebElement> descriptionDetails = driver.findElements(By.xpath("//h1[text()=' About this item ']/following::ul/li/span"));
        for(int i=0; i<descriptionDetails.size(); i++) {
            System.out.println(descriptionDetails.get(i).getText());
        }
    }
}
