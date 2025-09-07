@UserAccount
Feature: User Account Management

  As a user
  I want to register and log in to the application
  So that I can manage my account and access features

  @Signup @Positive
  Scenario Outline: Successful user registration with valid data
    Given the user is on the Home page
    When the user clicks on Sign up
    And the user enters "<username>" and "<password>" in the signup form
    And submits the registration form
    Then the user should see a "Sign up successful." alert for signup

    Examples:
      | username   | password |
      | mayan@exmp | pass123  |
      | raman@exmp | Pass456! |

  @Login @Positive
  Scenario Outline: Successful login with valid credentials
    Given the user is on the Home page
    When the user clicks on Log in
    When the user enters "<username>" and "<password>" in the login form
    And submit the login button
    Then the user should see a Home page

    Examples:
      | username    | password |
      | mayank@exmp | pass123  |
      | raman@exmp  | Pass456! |

  @Login @Negative
  Scenario Outline: Login fails with invalid credentials
    Given the user is on the Home page
    When the user clicks on Log in
    And the user enters "<username>" and "<password>" in the login form
    And submit the login button
    Then the user should see a "<errorMessage>" alert for login

    Examples:
      | username | password | errorMessage |
      | error12  | pass123  | User does not exist. |
      | admin    | pass456! | Wrong password.      |

