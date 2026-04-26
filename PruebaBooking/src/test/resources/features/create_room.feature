Feature: Create room

  Scenario: Create a room successfully
    Given the user has the room information
      | code | name         | city     | maxGuests | nightlyPrice | available |
      | 26   | HabitacionQA | Medellin | 2         | 100          | true      |
    When the user creates the room
    Then the room should be created successfully