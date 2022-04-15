package TestCases;

import Pages.AmazonPage;
import org.testng.annotations.Test;

public class TestAmazonWebSite extends BaseTest {

    @Test
    public void navigateToAmazonPage() {
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.launchBrowser();
        amazonPage.waitForPageToDisplay();
    }

    @Test(dependsOnMethods = "navigateToAmazonPage")
    public void filterForSamsungTV() {
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.clickBurgerButton();
        amazonPage.clickTVAndAppliances();
        amazonPage.clickTelevisionButtonSelectSamsung();
    }

    @Test(dependsOnMethods = "filterForSamsungTV")
    public void openSecondHighestPricedTV() {
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.clickSecondHighestProduct();
        amazonPage.switchToNewTab();
        amazonPage.verifyAndPrintSpecification();
    }
}
