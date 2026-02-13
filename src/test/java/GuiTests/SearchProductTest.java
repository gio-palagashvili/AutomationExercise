package GuiTests;

import GuiPages.RegisterUserPage;
import GuiPages.SearchedProductsPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Storefront UI")
@Feature("Web")
public class SearchProductTest {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON ProductName = new SHAFT.TestData.JSON(System.getProperty("ProductNameTestData"));

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @Description("Go to products, type search term and submit; results page shows searched products heading and matching items.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Catalog")
    @Test(description = "Product search shows results page and matching products")
    public void Verify_Searched_Products_is_visible() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnProductsButton()
                .Enter_product_name_in_search_input(ProductName.getTestData("ProductName"))
                .Click_On_Search_Button();

        driver.verifyThat()
                .element(SearchedProductsPage.Searched_Products_LocatorVisible())
                .isVisible()
                .withCustomReportMessage("Searched products section is visible")
                .perform();

        driver.verifyThat()
                .element(SearchedProductsPage.ProductName_Locator())
                .text()
                .isEqualTo(ProductName.getTestData("ProductName"))
                .withCustomReportMessage("Result matches search term")
                .perform();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
