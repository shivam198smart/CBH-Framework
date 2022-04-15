import Pages.AmazonPage;
import org.testng.annotations.Test;

public class TestScript extends BaseTest {

    @Test
    public void navigateToAmazonPage() {
        AmazonPage amazonPage = new AmazonPage(driver);
        amazonPage.launchBrowser();
        amazonPage.waitForPageToDisplay();
        amazonPage.clickBurgerButton();
        amazonPage.clickTVAndAppliances();
        amazonPage.clickTelevisionButton();
        amazonPage.clickSecondHighestProduct();
        amazonPage.switchToNewTab();
        amazonPage.verifyAndPrintSpecification();
    }
}
