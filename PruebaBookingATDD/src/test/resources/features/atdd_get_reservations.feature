Feature: Reservations acceptance query

  Scenario: User views all reservations successfully
    When the user requests the list of reservations
    Then the reservations list should be displayed successfully
    And each reservation should contain useful booking information