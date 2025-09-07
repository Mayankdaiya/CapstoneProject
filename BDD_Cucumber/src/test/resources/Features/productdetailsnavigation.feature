@Product @Navigation
Feature: Navigate categories and verify product details

  This feature ensures users can navigate through product categories
  and verify product information matches what was selected.

  @CategoryNavigation
  Scenario: User navigates through all product categories
    Given the user is on the Home page
    When the user navigates to "Phones" category
    Then the "Phones" product list should be displayed

    When the user navigates to "Laptops" category
    Then the "Laptops" product list should be displayed

    When the user navigates to "Monitors" category
    Then the "Monitors" product list should be displayed

  @ProductVerification @Regression
  Scenario Outline: User verifies product details from a selected category
    Given the user is on the Home page
    When the user navigates to "<category>" category
    And the user selects the product "<productName>"
    Then the product detail page should display "<productName>"
    And the product price should be "<price>"
    And the product description should contain "<description>"

    Examples:
      | category | productName       | price   | description |
      | Phones   | Samsung galaxy s6 | $360    | Samsung Exynos 7420 processor |
      | Laptops  | Sony vaio i5      | $790    | superior ultraportable laptop |
      | Monitors | Apple monitor 24  | $400    | LED Cinema Display    |
