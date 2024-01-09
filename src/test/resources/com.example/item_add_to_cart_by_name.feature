Feature: Adding the product by name functionality

  @positive
  Scenario: User adds the product by name
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    When user adds the product by name "Bike"
    When user click the cart link
    Then cart container page opens
    Then cart container page contains the product with name "Bike"