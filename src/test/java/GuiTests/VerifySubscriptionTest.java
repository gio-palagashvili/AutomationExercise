package GuiTests;

import GuiPages.RegisterUserPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static GuiTests.LoginUserTest.LoginUserTestData;

@Epic("Storefront UI")
@Feature("Web")
public class VerifySubscriptionTest {
    SHAFT.GUI.WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }


    @Description("On home page enter email in footer subscription field and submit; success message for subscription is displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Newsletter")
    @Test(description = "Footer email subscription shows success message")
    public void Verify_Subscription_in_home_page() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .Enter_email_address_in_input(LoginUserTestData.getTestData("EmailAddress"));


        driver.verifyThat()
                .element(RegisterUserPage.success_subscribe_Message())
                .text()
                .contains("You have been successfully subscribed!")
                .withCustomReportMessage("Subscription success text is present")
                .perform();

        driver.verifyThat()
                .element(RegisterUserPage.success_subscribe_Message())
                .isVisible()
                .withCustomReportMessage("Subscription success message is visible")
                .perform();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
