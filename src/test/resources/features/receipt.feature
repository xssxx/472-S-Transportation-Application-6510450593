Feature: Receipt View

  Scenario: Admin views a customer's receipt successfully
    Given an order for customer "Jane Doe"
    And I am logged in as admin
    When I view the receipt
    Then the receipt should be returned successfully and display the customer name "Jane Doe"

  Scenario: Customer views their own receipt successfully
    Given an order for customer "John Doe"
    And I am logged in as customer "John Doe"
    When I view the receipt
    Then the receipt should be returned successfully and display the customer name "John Doe"

  Scenario: Customer attempts to view another customer's receipt
    Given an order for customer "Jane Doe"
    And I am logged in as customer "John Doe"
    When I view the receipt
    Then it should throw an IllegalArgumentException
