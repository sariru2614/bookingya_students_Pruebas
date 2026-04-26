Feature: Create guest

  Scenario: Create a guest successfully
    Given the user has the guest information
      | identification | name         | email                         |
      | 1415           | PruebaQA1415 | PruebaQA1415@PruebaQA1415.com |
    When the user creates the guest
    Then the guest should be created successfully