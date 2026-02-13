package GuiTests;

import GuiPages.ContactUsPage2;
import GuiPages.RegisterUserPage;
import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Storefront UI")
@Feature("Web")
public class ContactUsFormTest {
    SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON ContactUsTestData = new SHAFT.TestData.JSON(System.getProperty("ContactUsTestData"));

    @BeforeMethod
    public void beforeMethod() {
        driver = new SHAFT.GUI.WebDriver();
    }


    @Description("Open contact form, fill name, email, subject, message and attach file; submit and confirm success message appears.")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Contact")
    @Test(description = "Contact form submission shows success message")
    public void ContactUsForm() {
        new RegisterUserPage(driver)
                .NavigateToUrl()
                .ClickOnContactUsButton()
                .Enter_name_email_subject_and_message(
                        ContactUsTestData.getTestData("Name"),
                        ContactUsTestData.getTestData("Email"),
                        ContactUsTestData.getTestData("Subject"),
                        ContactUsTestData.getTestData("Message"),
                        ContactUsTestData.getTestData("filePath"))
                .ClickOnSubmit();


        driver.assertThat()
                .element(ContactUsPage2.status_alert_success_Locator())
                .text()
                .contains("Success! Your details have been submitted successfully.")
                .withCustomReportMessage("Success confirmation message is shown")
                .perform();
    }


    @AfterMethod
    public void AfterMethod() {
        driver.quit();
    }
}
