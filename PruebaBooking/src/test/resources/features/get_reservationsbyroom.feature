Feature: Get reservations by room

  Scenario: Get reservations by room successfully
    When the user gets reservations by room id
      | roomId                               |
      | eb7b0ae8-dbb0-47a8-b41a-de72cde55606 |
    Then the reservations by room should be returned successfully