# Entrata_Assignment

This project includes testing scenarios for Entrata website, which uses Selenium and JUnit.

## Test Cases

### 1. Landing Page - Title Validation

- **Description:** Navigates to the landing page and validates the page Title as "Property Management Software | Entrata"
- **Test Method:** SeleniumTests.EntrataTests.testPageTitle

### 2. Sign in page - Invalid Credentials Validation

- **Description:** Navigates to Sign In page and checks error when password is empty
- **Test Method:** SeleniumTests.EntrataTests.testInvalidCredentials

### 3. Watch Demo page - Email field format validation

- **Description:** Navigates to Watch Demo form page and fills form with incorrect email format and checks error message
- **Test Method:** SeleniumTests.EntrataTests.testEmailFieldFormat

### 4. Watch Demo Page - Entrata Logo Validation 

- **Description:** Navigates to Watch Demo form page and validates the Entrata logo by checking if it is displayed and validating its text.
- **Test Method:** SeleniumTests.EntrataTests.testValidateLogoOnWatchDemoPage

## Prerequisites

- Java Development Kit (JDK)
- Selenium
- JUnit
- Maven
- WebDriver executable (e.g., chromedriver.exe)

Run mvn Verify command

