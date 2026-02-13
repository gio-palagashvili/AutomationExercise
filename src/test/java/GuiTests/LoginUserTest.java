package GuiTests;

import GuiPages.RegisterUserPage;
import GuiPages.SignInPage;
import GuiPages.SignUpAndloginPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Storefront UI")
@Feature("Web")
public class LoginUserTest {
    private SHAFT.GUI.WebDriver driver;

    public static SHAFT.TestData.JSON LoginUserTestData = new SHAFT.TestData.JSON(System.getProperty("SignInTestData"));

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @Description("Navigate to site, open login, submit valid credentials; user is logged in and username appears in header.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Valid login shows username in header")
    public void Login_User_with_correct_email_and_password() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .LoginUsingValidNameAndPassword(LoginUserTestData.getTestData("EmailAddress"), LoginUserTestData.getTestData("Password"))
                .ClickOnLoginButton();

        driver.assertThat()
                .element(SignInPage.VisibleUserNameLocator())
                .text()
                .contains("Ahmed")
                .withCustomReportMessage("Header shows logged-in username")
                .perform();
    }

    @Description("Submit wrong email/password; UI shows the invalid credentials error.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Invalid credentials show error message")
    public void Login_User_with_Incorrect_email_and_password() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .LoginUsingValidNameAndPassword(LoginUserTestData.getTestData("EmailAddress") + "invalidEmail", LoginUserTestData.getTestData("Password"))
                .ClickOnLoginButton();

        driver.assertThat()
                .element(SignUpAndloginPage.ErrorMessageLocator())
                .text()
                .contains("Your email or password is incorrect!")
                .withCustomReportMessage("Error text for invalid login is visible")
                .perform();
    }


    @Description("Login with valid user then click Logout; user is returned to login screen.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Auth")
    @Test(description = "Logout returns to login page")
    public void Logout_User() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .LoginUsingValidNameAndPassword(LoginUserTestData.getTestData("EmailAddress"), LoginUserTestData.getTestData("Password"))
                .ClickOnLoginButton()
                .ClickOnLogOutButton();

        driver.assertThat()
                .element(RegisterUserPage.LogintoyouraccountLocator())
                .isVisible()
                .withCustomReportMessage("Login page is shown after logout")
                .perform();

    }

    @Test(description = "Signup with already registered email shows error")
    public void Register_User_with_existing_email() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnSignupButton()
                .AddNewUserSignup(LoginUserTestData.getTestData("Password"), LoginUserTestData.getTestData("EmailAddress"))
                .ClickOnSignUpButton();

        driver.assertThat()
                .element(SignUpAndloginPage.ErrorMessageEmailExist())
                .text()
                .contains("Email Address already exist!")
                .withCustomReportMessage("Duplicate email error is displayed")
                .perform();

    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}

