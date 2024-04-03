This repository contains automated Behavior-Driven Development tests for a [Contact List Web Application](https://thinking-tester-contact-list.herokuapp.com/). The tests cover Create, Read, Update, and Delete (CRUD) scenarios for both the User Interface (UI) and REST API endpoints of the application.

## About the Application Under Test

[Contact List Web Application](https://thinking-tester-contact-list.herokuapp.com/) is designed solely for testing purposes and does not contain any actual data. It provides users with the functionality to manage their contact lists, allowing them to create, edit, or delete contacts as needed. Authentication is required for users to access and utilize the application's features.

Furthermore, the application provides an accessible [REST API](https://documenter.getpostman.com/view/4012288/TzK2bEa8) designed for testing purposes.

## Tech Stack
- [**Java**](https://www.oracle.com/ca-en/java/): The primary programming language for test automation.
- [**Selenium**](https://www.selenium.dev): Used for browser automation and interaction with web elements.
- [**Serenity BDD**](https://serenity-bdd.github.io): An open-source library for creating clear, concise, and maintainable automated acceptance tests.
- [**Cucumber**](https://cucumber.io): A tool for running automated tests written in plain language.
- [**Rest Assured**](https://rest-assured.io): A Java library for testing RESTful APIs.
- [**Maven**](https://maven.apache.org): Dependency management and build automation tool.
- [**JUnit**](https://junit.org/junit5/): Testing framework for Java programming language.

## Getting Started
To run the automated tests locally, ensure you have the following prerequisites installed:

- [Java Development Kit (JDK)](https://www.oracle.com/ca-en/java/technologies/downloads/)
- [Maven](https://maven.apache.org/install.html)
- [Chrome web browser](https://www.google.com/chrome/)

Clone this repository to your local machine using:

`git clone https://github.com/your-username/contact-list-tests.git`

Navigate to the project directory:

`cd contact-list-tests`

Run the tests using Maven:

`mvn clean verify`

## Project structure

This project follows a standard Maven structure and is organized as follows:
```
.
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── data
│   │   │   ├── model
│   │   │   ├── service
│   │   │   ├── tests
│   │   │   ├── ui
│   │   │   ├── utils
│   │   ├── resources
└── pom.xml
```

- `src/test/java/data`: This directory contains classes that generate data for testing.
- `src/test/java/model`: This directory contains classes that model the data entities used in the application, such as User and Contact.
- `src/test/java/service`: This directory contains classes that interact with the REST API of the Contact List Web Application.
- `src/test/java/tests`: This directory contains the test classes. Each class corresponds to a test suite, and each method in a class corresponds to a test case. Additionally, this directory encompasses step definitions that map the human-readable Gherkin language to Java code, and a datasource that provides data for the tests.
- `src/test/java/ui`: This directory contains classes that represent the pages of the Contact List Web Application, as well as web elements specific to the application.
- `src/test/java/utils`: This directory contains utility classes that are used across the project.
- `src/test/resources`: This directory contains Cucumber feature files.
- `pom.xml`: Maven Project Object Model file. It contains information about the project and configuration details used by Maven to build the project.

## UI tests

### Page Object Model

The UI tests in this project are designed using the Page Object Model (POM), a design pattern that enhances test maintenance and reduces code duplication. A page object is an object-oriented class that serves as an interface to a page of the application under test.

The BasePage class is the parent class for all page objects, providing common functionality that's shared across multiple pages. This includes methods for checking if a page is open, navigating to a page, and waiting for a page to load.

Custom WebElement classes like `ReadOnlyField`, `TextFormField`, and `ContactForm` are used to encapsulate specific behavior of these elements in the application. These classes extend from Serenity classes, which provide a rich API for handling complex interactions with the web elements.

Page objects are injected into test classes, providing a clear and concise way to write tests. Each page object encapsulates the elements of a particular page and provides methods to interact with these elements.

### BDD approach

The UI tests themselves are written in Gherkin language in feature files, providing a high-level description of the application's behavior.

```
Given I am a registered user in the application:
| First Name    | Last Name   | Email                         | Password       |  
| Norman        | Stern       | NormanDStern@jourrapide.com   | hu6aeYaipha    |
And I am signed in to the application

When I click on the "Logout" button

Then I should see a Main Page with the login form
```

Step definitions map these Gherkin steps to Java code, which interacts with the page objects to execute the tests. This approach allows for clear communication of what each test is supposed to do, making the tests easier to understand and maintain.

```
@When("I click on the \"Logout\" button")
public void iClickLogoutButton() {
	contactListPage.clickLogoutButton();
}
```

### Test data

In the Gherkin scenario, the test data is provided in a tabular format right after the Given step. Each column in the table corresponds to a field of the User or Contact object, such as "First Name", "Last Name", "Date of Birth", etc. Each row in the table represents a different User/Contact object.

```
| First Name    | Last Name   | Date of Birth | Email                             | Phone         | Street Address 1     | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
| Gertrude      | Robinson    | 1966-08-22    | GertrudeBRobinson@teleworm.us     | 303-669-1342  | 1476 Clearview Drive |                  | Denver       | CO                  | 80216          | United States |
```

### Set up and tear down

To expedite tests' run time, the setup and teardown stages for UI test scenarios are performed using REST API calls. This approach is typically faster than performing these operations through the UI, leading to more efficient tests.

During the setup phase, necessary entities such as the user and contacts are created via API calls. Similarly, the teardown phase involves cleaning up the data created during the setup or test execution. This includes deleting the user created for the test scenario.

## REST API tests

### Contacting the web service end-points

The REST API tests in this project utilize the RestAssured framework, a powerful tool for testing and validating REST services in Java. RestAssured simplifies the process of sending HTTP requests to REST endpoints and verifying the responses.

The application has specific classes, namely `ContactErrorResponse` and `ContactSuccessResponse`, for deserializing the JSON response body from the web service endpoints into Java objects. Response classes contain convenience methods to convert the deserialized data into Contact model objects.

### Test data

Contact data for the tests is primarily sourced from the [Random User Generator](https://randomuser.me) web service. This service provides random user information, which is then transformed into Contact model objects suitable for the tests.

In case Random User Generator web service is unavailable, the test framework is designed to fall back to a local JSON file. This file contains approximately 500 dummy contacts, providing a robust set of data for the tests to continue running even in the absence of the external web service.

The test user data, which includes the email and password, is dynamically generated for each test run using a utility method.

### Parameterization

Certain test classes, like `AddContactRestPositiveTest` and `AddContactRestNegativeTest` are parametrized which allows to run the same scenario multiple times with different input values. This approach reduces the amount of boilerplate code, making the tests more concise and easier to read and maintain.

## Reporting

After a test run, Serenity generates a detailed report that includes information about the executed tests, their results, and any associated data.

The report provides a summary of all executed tests, including the number of tests that passed, failed, or were pending.

Each test is broken down into its individual steps, providing a detailed view of what each test does. This is particularly useful for understanding the flow of complex tests.

The generated report is a set of static HTML files, so it can be easily deployed as a website on any web server.

For reference, you can view [the most recent test report](https://juliab.github.io/contact-list-webapp-tests/target/site/serenity/).
