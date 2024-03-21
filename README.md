This repository contains automated Behavior-Driven Development tests for a [Contact List Web Application](https://thinking-tester-contact-list.herokuapp.com/). 

## About the Contact List Application

Contact List Application is designed solely for testing purposes and does not contain any actual data. It provides users with the functionality to manage their contact lists, allowing them to create, edit, or delete contacts as needed. Authentication is required for users to access and utilize the application's features.

Furthermore, the application provides an accessible [REST API](https://documenter.getpostman.com/view/4012288/TzK2bEa8) designed for testing purposes.

## Tech Stack
- **Java**: The primary programming language for test automation.
- **Selenium**: Used for browser automation and interaction with web elements.
- **Serenity BDD**: An open-source library for creating clear, concise, and maintainable automated acceptance tests.
- **Cucumber**: A tool for running automated tests written in plain language.
- **Rest Assured**: A Java library for testing RESTful APIs.
- **Maven**: Dependency management and build automation tool.

## Getting Started
To run the automated tests locally, ensure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Maven
- Chrome web browser

Clone this repository to your local machine using:

`git clone https://github.com/your-username/contact-list-tests.git`

Navigate to the project directory:

`cd contact-list-tests`

Run the tests using Maven:

`mvn clean verify`
