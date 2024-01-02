Feature: Place the order for Products

  Scenario Outline: Search Experience for product search in both home and Offers page

    Given User is on GreenCart Landing page
    When user searched with Shortname "<Name>" and extracted actual name of product
    Then Added "3" items of the selected product to cart
    Then user Checkout and validate the "<Name>" items in checkout page
    And Verify the place order page
    #Then Close the webdriver

    Examples:
      | Name  |
      | Tom   |