package com.project.bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservationsByGuest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AtddGetReservationsByGuestStepDefinitions {

    private final Actor guest = Actor.named("Guest");
    private String expectedGuestId;

    @When("the guest requests their reservations")
    public void theGuestRequestsTheirReservations(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);
        expectedGuestId = data.get("guestId");

        System.out.println("========== ATDD STEP: GET RESERVATIONS BY GUEST ==========");
        System.out.println("Guest ID requested: " + expectedGuestId);

        guest.attemptsTo(
                GetReservationsByGuest.withId(expectedGuestId)
        );

        System.out.println("Response status: " + SerenityRest.lastResponse().statusCode());
        System.out.println("Response body: " + SerenityRest.lastResponse().asString());
    }

    @Then("the guest reservations should be displayed successfully")
    public void theGuestReservationsShouldBeDisplayedSuccessfully() {

        assertEquals(200, SerenityRest.lastResponse().statusCode());

        List<Map<String, Object>> reservations = SerenityRest.lastResponse()
                .jsonPath()
                .getList("$");

        assertNotNull("Reservations list should not be null", reservations);
        assertFalse("Reservations list should not be empty", reservations.isEmpty());

        System.out.println("Guest reservations displayed successfully");
    }

    @And("each reservation should belong to the requested guest")
    public void eachReservationShouldBelongToTheRequestedGuest() {

        List<Map<String, Object>> reservations = SerenityRest.lastResponse()
                .jsonPath()
                .getList("$");

        for (Map<String, Object> reservation : reservations) {
            assertEquals(
                    "Reservation should belong to requested guest",
                    expectedGuestId,
                    reservation.get("guestId")
            );

            assertNotNull("Room ID should not be null", reservation.get("roomId"));
            assertNotNull("CheckIn should not be null", reservation.get("checkIn"));
            assertNotNull("CheckOut should not be null", reservation.get("checkOut"));
            assertNotNull("Guests count should not be null", reservation.get("guestsCount"));
        }

        System.out.println("All reservations belong to the requested guest");
    }
}