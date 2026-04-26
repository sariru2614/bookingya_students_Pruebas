Feature: Create reservation

  Scenario: Create a reservation successfully
    Given the user has a created guest
      | identification | name         | email                         |
      | 2006           | PruebaQA2006 | PruebaQA2006@PruebaQA2006.com |
    And the user has a created room
      | code | name         | city     | maxGuests | nightlyPrice | available |
      | 3006 | HabitacionQA | Medellin | 2         | 100          | true      |
    When the user creates the reservation
      | checkIn                  | checkOut                 | guestsCount | notes  |
      | 2026-04-26T02:40:01.104Z | 2026-05-26T02:40:01.104Z | 1           | people |
    Then the reservation should be created successfully