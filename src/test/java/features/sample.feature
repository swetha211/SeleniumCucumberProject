Feature: Place the order for Products

  Scenario Outline: Search Experience for product search in both home and Offers page

    Given User is on sample Landing page
    Then validate the Error message

    Examples:
      | Name  |
      | Tom   |
