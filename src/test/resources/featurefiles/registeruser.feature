Feature: testing registration of a new user

  Scenario: Successfully registering a new user account
    Given the user is on the landing page
    When the login/register button is selected
    And an email address is entered
    | ml4690@outlook.com |
