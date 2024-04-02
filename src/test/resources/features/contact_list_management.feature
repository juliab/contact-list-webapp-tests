@Contacts
Feature: Contact list management

@ContactsPositive
Scenario Outline: Add new contact to the contact list
    
    Given I am on the contact list page
    
    When I click on the "Add a New Contact" button
    And I fill in the following contact details:
      | First Name    | Last Name   | Date of Birth | Email      | Phone         | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country     |
      | <firstName>   | <lastName>  | <dob>         | <email>    | <phone>       | <address1>         | <address2>       | <city>       | <stateOrProvince>   | <postalCode>   | <country>   |
    And I click on the "Submit" button to add a contact
    
    Then I should see added contact in the contact list
    
    Examples:
    | firstName     | lastName    | dob           | email                            | phone         | address1         | address2    | city         | stateOrProvince     | postalCode     | country         |
    | John          | Doe         | 1990-05-15    | johndoe@email.com                | 34367899090   | 123 Main St      |             | Austin       | Texas               | 73301          | United States   |
    | William       | Batista     | 1986-08-26    | WilliamMBatista@jourrapide.com   | 907-568-3918  |                  |             |              |                     |                |                 |

@ContactsNegative
Scenario: Try to add new contact with empty first and last name to the contact list
		
		Given I am on the contact list page
    
    When I click on the "Add a New Contact" button
    And I fill in the following contact details:
      | First Name    | Last Name   | Date of Birth | Email               | Phone     | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country     |
      |               |             | 1990-05-15    | johndoe@email.com   |           |                    |                  |              |                     |                |             |
    And I click on the "Submit" button to add a contact
    
    Then I should see the error message: "Contact validation failed: firstName: Path `firstName` is required., lastName: Path `lastName` is required."

@ContactsPositive
Scenario: Edit existing contact from the contact list

		Given my contact list contains one contact with the following details:
			| First Name    | Last Name   | Date of Birth | Email                             | Phone         | Street Address 1   | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
		    | Deborah       | Williams    | 1983-08-13    | DeborahEWilliams@jourrapide.com   | 580-251-4338  | 2582 Luke Lane     |                  | Duncan       | OK                  | 73533          | United States |
		And I am on the contact list page
		
		When I click on the contact row
		And I click on the "Edit Contact" button
		And I change the contact details to the following:
			| First Name    | Last Name   | Date of Birth | Email                             | Phone         | Street Address 1     | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
		    | Deborah       | Williams    | 1983-08-13    | DeborahEWilliams@jourrapide.com   |               | 3744 Station Street  | Apartment 2C     | Oakland      | California          | 94612          | United States |
		And I click on the "Submit" button to edit a contact
		
		Then I should see updated contact details on the page

@ContactsNegative
Scenario: Try to set empty first and last name to the existing contact

		Given my contact list contains one contact with the following details:
			| First Name    | Last Name   | Date of Birth | Email                             | Phone         | Street Address 1     | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
		  | Gertrude      | Robinson    | 1966-08-22    | GertrudeBRobinson@teleworm.us     | 303-669-1342  | 1476 Clearview Drive |                  | Denver       | CO                  | 80216          | United States |
		And I am on the contact list page
		
		When I click on the contact row
		And I click on the "Edit Contact" button
		And I change the contact details to the following:
			| First Name    | Last Name   | Date of Birth | Email                             | Phone         | Street Address 1     | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
		  |               |             | 1966-08-22    | GertrudeBRobinson@teleworm.us     | 303-669-1342  | 1476 Clearview Drive |                  | Denver       | CO                  | 80216          | United States |
		And I click on the "Submit" button to edit a contact
		
		Then I should see the error message: "Validation failed: lastName: Path `lastName` is required., firstName: Path `firstName` is required."

@ContactsPositive
Scenario: Delete a contact from the contact list

		Given my contact list contains one contact with the following details:
			| First Name    | Last Name   | Date of Birth | Email                        | Phone         | Street Address 1     | Street Address 2 | City         | State or Province   | Postal Code    | Country       |
		  | Joshua        | Smith       | 1939-01-30    | JoshuaSSmith@dayrep.com      | 917-712-5808  | 1642 Hanover Street  |                  | New York     | NY                  | 10016          | United States |
		And I am on the contact list page
		
		When I click on the contact row
		And I click on the "Delete Contact" button
		And I accept the confirmation dialog
		
		Then I should return to a Contact List page
		And I should not see the deleted contact in my contact list