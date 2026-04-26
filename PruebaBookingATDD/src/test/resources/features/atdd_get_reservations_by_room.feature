Feature: Reservations acceptance query by room

  Scenario: User views reservations assigned to a room successfully
    When the user requests reservations for a room
      | roomId                               |
      | eb7b0ae8-dbb0-47a8-b41a-de72cde55606 |
    Then the room reservations should be displayed successfully
    And each reservation should belong to the requested room