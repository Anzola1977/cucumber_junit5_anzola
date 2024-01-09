Feature: Cart container page and checkout functionality

  @positive
  Scenario: User checkouts the product
    Given user enter login page
    When user enters valid credentials
    And clicks login button
    Then main page opens
    When user adds first product to the cart
    Then amount of products in the cart is 1
    When user click the cart link
    Then cart container page opens
    When user clicks checkout button
    Then checkout page one opens
    When user enters first name
    And enters last name
    And enters zip code
    And clicks continue
    Then checkout page two opens
    When user clicks finish button
    Then checkout complete page opens
    And it have text in header "THANK YOU FOR YOUR ORDER"

