package GuiTests;

import GuiPages.AccountCreatedPage;
import GuiPages.RegisterUserPage;
import GuiPages.SignUpAndloginPage;
import GuiPages.SignUpPage;
import Utils.Util;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Storefront UI")
@Feature("Web")
public class RegisterUserTest {

    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON SignUptestData = new SHAFT.TestData.JSON(System.getProperty("SignUpTestData"));
    private SHAFT.TestData.JSON AccountDetailstestData = new SHAFT.TestData.JSON(System.getProperty("AccountDetailsTestData"));

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();

    }

    @Description("Load base URL; landing page loads and main slider/banner is visible.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Landing page loads and main content is visible")
    public void Verify_that_home_page_is_visible_successfully() {

        new RegisterUserPage(driver)
                .NavigateToUrl();

        driver.assertThat()
                .element(RegisterUserPage.SliderLocator())
                .isVisible()
                .withCustomReportMessage("Home page main area is visible")
                .perform();
    }

    @Description("From home, open Signup/Login; signup section with name and email fields is shown.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Signup section is visible from nav")
    public void Verify_New_User_Signup_is_visible() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton();

        driver.assertThat()
                .element(SignUpAndloginPage.NewUserSignupLocator())
                .isVisible()
                .withCustomReportMessage("New user signup block is visible")
                .perform();
    }

    @Description("Enter name and email on signup, submit; account information step is displayed.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "After signup submit, account info step is shown")
    public void Verify_that_Enter_Account_Information_is_visible() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .AddNewUserSignup
                        (
                                SignUptestData.getTestData("name") + Util.GenerateCurrentDateAndTime(),
                                SignUptestData.getTestData("SignUpEmail") + Util.GenerateCurrentDateAndTime()
                        )
                .ClickOnSignUpButton();

        driver.assertThat()
                .element(SignUpPage.EnterAccountInformationLocator())
                .isVisible()
                .withCustomReportMessage("Account information section is visible")
                .perform();
    }

    @Description("Complete signup and account details, create account; confirmation screen shows account created.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Full registration shows account created confirmation")
    public void Verify_that_Account_Created_is_visible() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .AddNewUserSignup
                        (
                                SignUptestData.getTestData("name") + Util.GenerateCurrentDateAndTime(),
                                SignUptestData.getTestData("SignUpEmail") + Util.GenerateCurrentDateAndTime()
                        )
                .ClickOnSignUpButton()
                .FillAccountDetails
                        (
                                AccountDetailstestData.getTestData("Name"),
                                AccountDetailstestData.getTestData("Password"),
                                Integer.parseInt(AccountDetailstestData.getTestData("Day")),
                                AccountDetailstestData.getTestData("Month"),
                                Integer.parseInt(AccountDetailstestData.getTestData("Year")),
                                AccountDetailstestData.getTestData("FirstName"),
                                AccountDetailstestData.getTestData("LastName"),
                                AccountDetailstestData.getTestData("Company"),
                                AccountDetailstestData.getTestData("Address1"),
                                AccountDetailstestData.getTestData("Address2"),
                                AccountDetailstestData.getTestData("Country"),
                                AccountDetailstestData.getTestData("State"),
                                AccountDetailstestData.getTestData("City"),
                                Integer.parseInt(AccountDetailstestData.getTestData("Zipcode")),
                                Integer.parseInt(AccountDetailstestData.getTestData("MobileNumber"))
                        )
                .ClickOnCreateAccountButton();


        driver.assertThat()
                .element(AccountCreatedPage.AccountCreatedLocator())
                .isVisible()
                .withCustomReportMessage("Account created confirmation is visible")
                .perform();

    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
