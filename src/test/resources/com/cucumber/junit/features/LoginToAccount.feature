Feature: As a user I want to get ability to login to account

  Scenario: Successful login to account
    Given the user opens bookdepository site
    And clicks on the Sign in button on navigation bar
    When the user fills in the login and password
    Then the user is logged in