Feature: User authentication

@SigninPositive 
Scenario: Successful sign in to the system
    
    Given I am a registered user in the application:
      | First Name    | Last Name   | Email                         | Password       |  
      | Rose          | Chilton     | RoseLChilton@teleworm.us      | aeGh8dae2t     |
    And I am on the main page
    
    When I fill in my email and password into the login form
    And I click on the "Submit" button to login
    
    Then I should see a Contact List page with a Logout button
    
@SigninNegative    
Scenario: Try to sign in with non-existing user data
    
    Given I am not a registered user in the application:
      | First Name    | Last Name   | Email                         | Password       |  
      | Nancy         | Jones       | NancyTJones@teleworm.us       | aeGh8dae2t     |
    And I am on the main page
    
    When I fill in my email and password into the login form
    And I click on the "Submit" button to login
    
    Then I should see the error message: "Incorrect username or password"
 
@SigninPositive
Scenario: Successful sign out of the system
    
    Given I am a registered user in the application:
      | First Name    | Last Name   | Email                         | Password       |  
      | Norman        | Stern       | NormanDStern@jourrapide.com   | hu6aeYaipha    |
    And I am signed in to the application
    
    When I click on the "Logout" button
    
    Then I should see a Main Page with the login form
    
