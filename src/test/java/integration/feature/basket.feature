Feature: Basket
  As a user
  I want to collect products into my basket
  So that I can checkout them out together

  Scenario: Get the basket
    Given The basket contains '2' products
    When I ask for the basket
    Then I get a list with '2' products

  Scenario: Add a product
    Given The basket contains '0' products
    When I add a product to the basket
    And I ask for the basket
    Then I get a list with '1' products
