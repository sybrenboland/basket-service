Feature: Basket
  As a user
  I want to collect products into my basket
  So that I can checkout them out together

  Scenario: Get the basket
    Given I have a basket
    When I ask for the basket
    Then I get a '200' response
