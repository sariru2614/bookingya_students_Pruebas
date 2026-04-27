Feature: Reservation acceptance

  Scenario: Guest successfully books an available room
    Given the guest is registered for booking
      | identification                  | name             | email             |
      | ${atddGuestIdentification}      | ${atddGuestName} | ${atddGuestEmail} |
    And the guest selects an available room
      | code           | name           | city     | maxGuests | nightlyPrice | available |
      | ${atddRoomCode}| ${atddRoomName}| Medellin | 2         | 100          | true      |
    When the guest confirms the booking
      | checkIn                  | checkOut                 | guestsCount | notes        |
      | 2026-07-01T14:00:00.000Z | 2026-07-05T11:00:00.000Z | 1           | Reserva ATDD |
    Then the booking should be confirmed
    And the booking details should match the guest request

#Escenario ATDD valida que la API funcione correctamente, Datos correctos y que el Usuario obtiene lo esperado.