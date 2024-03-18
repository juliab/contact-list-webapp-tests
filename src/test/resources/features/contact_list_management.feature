Feature: Contact list management

Scenario Outline: Add new contact to the contact list
    Given I am on the contact list page
    When I click on the "Add a New Contact" button
    And I fill in the following details:
      | First Name    | Last Name   | Date of Birth | Email      | Phone         | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country     |
      | <firstName>   | <lastName>  | <dob>         | <email>    | <phone>       | <address1>         | <address2>       | <city>       | <stateOrProvince>   | <postalCode>   | <country>   |
    And I click on the "Submit" button
    Then I should see added contact in the contact list
    
    Examples:
    | firstName     | lastName    | dob           | email                            | phone         | address1         | address2    | city         | stateOrProvince     | postalCode     | country         |
    | John          | Doe         | 1990-05-15    | johndoe@email.com                | 34367899090   | 123 Main St      |             | Austin       | Texas               | 73301          | United States   |
    | William       | Batista     | 1986-08-26    | WilliamMBatista@jourrapide.com   | 907-568-3918  |                  |             |              |                     |                |                 |

Scenario: Try to add new contact with empty first and last name to the contact list
		Given I am on the contact list page
    When I click on the "Add a New Contact" button
    And I fill in the following details:
      | First Name    | Last Name   | Date of Birth | Email               | Phone     | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country     |
      |               |             | 1990-05-15    | johndoe@email.com   |           |                    |                  |              |                     |                |             |
    And I click on the "Submit" button
    Then I should see a validation message: "Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required."