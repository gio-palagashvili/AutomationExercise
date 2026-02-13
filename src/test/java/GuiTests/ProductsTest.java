package GuiTests;

import GuiPages.ProductDetailPage;
import GuiPages.ProductsPage;
import GuiPages.RegisterUserPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Storefront UI")
@Feature("Web")
public class ProductsTest {
    private SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @Description("Open site, go to Products from nav; products listing page loads and heading is shown.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Catalog")
    @Test(description = "Products listing page loads and heading is displayed")
    public void Verify_user_is_navigated_to_ALL_PRODUCTS_page_successfully() {

        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnProductsButton();

        driver.verifyThat()
                .element(ProductsPage.ALlProductsLocator())
                .isVisible()
                .withCustomReportMessage("Products page heading is displayed")
                .perform();

        driver.verifyThat()
                .element(ProductsPage.products_list_Locator())
                .isVisible()
                .withCustomReportMessage("Product grid is visible")
                .perform();

    }

    @Description("From products list open first item; product details screen shows name, category, price, availability, condition, brand.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Catalog")
    @Test(description = "First product details page shows expected info")
    public void Verify_that_Product_detail_Is_visible() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnProductsButton()
                .Click_on_ViewProduct_of_first_product();

        driver.verifyThat()
                .element(ProductDetailPage.product_details_Locator())
                .isVisible()
                .withCustomReportMessage("Detail block visible: name, category, price, availability, condition, brand")
                .perform();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
