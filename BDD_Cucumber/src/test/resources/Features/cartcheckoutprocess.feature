@Cart @Checkout
Feature: Cart management and checkout process

  This feature ensures that users can add items to the cart,
  delete them, and successfully complete the checkout process.

  @AddToCart @Smoke
  Scenario Outline: Verify that user can add an item to the cart
    Given the user is on the Home page
    When the user navigates to "<category>" category
    And the user selects the product "<productName>"
    And the user clicks on Add to cart
    And the user navigates to the Cart page
    Then the cart should contain the product "<productName>"
    And a confirmation message should be displayed

    Examples:
      | category | productName         |
      | Phones   | Samsung galaxy s6   |
      | Laptops  | Sony vaio i5        |

  @DeleteFromCart @Regression
  Scenario: Verify that user can delete an item from the cart
    Given the user is on the Home page
    When the user navigates to the Cart page
    And the cart item list is displayed
    And the user deletes the first item from the cart
    Then the item deletes from the cart item list
    And the deleted item should no longer be in the cart

  @Checkout @Regression
  Scenario Outline: Verify the checkout process
    Given the user is on the Home page
    And the user has added "<productName>" from the "<category>" to the cart
    When the user navigates to the Cart page
    And the user clicks on Place Order
    And the user fills in the order details with:
      | Name    | <name>   |
      | Country | <country>|
      | City    | <city>   |
      | Card    | <card>   |
      | Month   | <month>  |
      | Year    | <year>   |
    And the user clicks on Purchase
    Then the order confirmation message should be displayed
    And the user clicks on OK

    Examples:
      | productName       | category | name     | country | city     | card       | month | year |
      | Samsung galaxy s6 | Phones   | Mayank   | India   | Mumbai   | 1234567890 | Sep   | 2025 |
