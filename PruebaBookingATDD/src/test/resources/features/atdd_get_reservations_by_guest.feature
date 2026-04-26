Feature: Reservations acceptance query by guest

  Scenario: Guest views their reservations successfully
    When the guest requests their reservations
      | guestId                              |
      | 54744713-dfa0-4e5f-be91-0ef9f7e2fe02 |
    Then the guest reservations should be displayed successfully
    And each reservation should belong to the requested guest