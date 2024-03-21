Feature: User sign-up

@SignupPositive
Scenario: Successfully add a new user to the system
    
    Given I am on the Add User page
    
    When I fill in the following user details:
      | First Name    | Last Name   | Email                         | Password       |  
      | Danielle      | Anderson    | DanielleAnderson@rhyta.com    | ooDiFahtuZ9i   |
    And I click on the "Submit" button to register a user
    
    Then I should see a Contact List page with a Logout button

@SignupNegative    
Scenario: Try to add a new user with empty First Name field
    
    Given I am on the Add User page
    
    When I fill in the following user details:
      | First Name    | Last Name   | Email                   | Password       |  
      |               | Doss        | MaryDoss@rhyta.com      | Aish4EeBee7    |
    And I click on the "Submit" button to register a user
    
    Then I should see the error message: "User validation failed: firstName: Path `firstName` is required."

@SignupNegative 
Scenario: Try to add a new user with an invalid email
    
    Given I am on the Add User page
    
    When I fill in the following user details:
      | First Name    | Last Name   | Email                | Password       |  
      | Paul          | Price       | PaulPrice@rhyta      | IokuN1oh       |
    And I click on the "Submit" button to register a user
    
    Then I should see the error message: "User validation failed: email: Email is invalid"