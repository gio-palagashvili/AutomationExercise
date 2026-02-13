# E‑commerce UI test suite

UI tests for a demo storefront. Target site: https://www.automationexercise.com/

## Stack

- **Java 17**, **Maven**
- **SHAFT** (Selenium + TestNG)
- **Allure** and **Extent** for reports
- Optional: Selenium Grid 4 / Docker for remote runs

## Structure

- **Page Object Model** for UI
- **Data-driven** tests (JSON test data)
- **Fluent** page APIs where applicable
- Javadoc on public page methods

## Scenarios covered

**Auth & registration**
- Landing page loads and main content is visible
- Signup / login entry point shows signup form
- After name+email signup, account info step appears
- Full registration flow shows “account created”
- Valid login shows username in header
- Invalid credentials show error
- Logout returns to login page
- Signup with existing email shows duplicate-email error

**Catalog**
- Products listing page loads and heading is displayed
- First product details page shows name, category, price, availability, condition, brand
- Product search shows results page and matching products

**Contact & newsletter**
- Contact form submission shows success message
- Footer email subscription shows success message

## Running tests

```bash
mvn test
```

Generate Allure report after a run:

```bash
mvn allure:serve
```

## IntelliJ + TestNG

If using TestNG from IntelliJ: in Run/Debug Configurations, under Templates → TestNG → Listeners, add the SHAFT listeners (AlterSuite, InvokedMethod, Suite) so reporting works correctly.
