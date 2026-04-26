package bookingya.unit_TDD;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReservationRulesTest {

    @Test
    public void checkInShouldBeBeforeCheckOut() {

        String checkIn = "2026-05-01";
        String checkOut = "2026-04-01";

        boolean isValid = checkIn.compareTo(checkOut) < 0;

        assertFalse(isValid);
    }
}