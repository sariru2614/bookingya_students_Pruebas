Feature: Get reservations by guest

  Scenario: Get reservations by guest successfully
    When the user gets reservations by guest id
      | guestId                              |
      | 54744713-dfa0-4e5f-be91-0ef9f7e2fe02 |
    Then the reservations by guest should be returned successfully