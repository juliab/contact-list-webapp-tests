Feature: Contact list management

Scenario: Add new contact to the contact list
    Given I am on the contact list page
    When I click on the "Add a New Contact" button
    And I fill in the following details:
      | First Name    | Last Name   | Date of Birth | Email                  | Phone         | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country         |
      | John          | Doe         | 1990-05-15    | johndoe@email.com      | 34367899090   | 123 Main St        |                  | Austin       | Texas               | 73301          | United States   |
    And I click on the "Submit" button
    Then I should see added contact in the contact list