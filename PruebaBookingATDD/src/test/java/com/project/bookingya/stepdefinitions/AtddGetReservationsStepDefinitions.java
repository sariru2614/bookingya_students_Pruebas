package com.project.bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservations;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AtddGetReservationsStepDefinitions {

    private final Actor user = Actor.named("User");

    @When("the user requests the list of reservations")
    public void theUserRequestsTheListOfReservations() {

        System.out.println("========== ATDD STEP: REQUEST RESERVATIONS LIST ==========");

        user.attemptsTo(
                GetReservations.fromApi()
        );

        System.out.println("Response status: " + SerenityRest.lastResponse().statusCode());
        System.out.println("Response body: " + SerenityRest.lastResponse().asString());
    }

    @Then("the reservations list should be displayed successfully")
    public void theReservationsListShouldBeDisplayedSuccessfully() {

        assertEquals(200, SerenityRest.lastResponse().statusCode());

        List<Map<String, Object>> reservations = SerenityRest.lastResponse()
                .jsonPath()
                .getList("$");

        assertNotNull("Reservations list should not be null", reservations);
        assertFalse("Reservations list should not be empty", reservations.isEmpty());

        System.out.println("Reservations list displayed successfully");
    }

    @And("each reservation should contain useful booking information")
    public void eachReservationShouldContainUsefulBookingInformation() {

        List<Map<String, Object>> reservations = SerenityRest.lastResponse()
                .jsonPath()
                .getList("$");

        Map<String, Object> firstReservation = reservations.get(0);

        assertNotNull("Guest ID should not be null", firstReservation.get("guestId"));
        assertNotNull("Room ID should not be null", firstReservation.get("roomId"));
        assertNotNull("CheckIn should not be null", firstReservation.get("checkIn"));
        assertNotNull("CheckOut should not be null", firstReservation.get("checkOut"));
        assertNotNull("Guests count should not be null", firstReservation.get("guestsCount"));

        System.out.println("First reservation contains useful booking information");
    }
}