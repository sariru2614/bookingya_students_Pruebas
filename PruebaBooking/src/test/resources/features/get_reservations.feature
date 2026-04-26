Feature: Get reservations

  Scenario: Get all reservations successfully
    When the user gets all reservations
    Then the reservations should be returned successfully