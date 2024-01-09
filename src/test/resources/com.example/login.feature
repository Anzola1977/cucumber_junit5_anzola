@top-level
Feature: Login Functionality

  @positive
  Scenario: Basic successful login scenario
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    And it have text in footer "2020 Sauce Labs. All Rights Reserved."

  @negative
  Scenario Outline: Negative login scenarios
    Given user enter login page
    When user enters login <login> and password "<password>"
    And clicks login button
    Then error message contains text "<error_message>"

    Examples:
      | login           | password     | error_message                               |
      | standard_user   | no_sauce     | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out        |
      | standard_user   |              | Epic sadface: Password is required                        |
