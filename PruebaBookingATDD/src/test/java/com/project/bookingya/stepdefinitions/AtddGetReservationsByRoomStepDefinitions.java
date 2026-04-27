package com.project.bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservationsByRoom;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AtddGetReservationsByRoomStepDefinitions {

    private final Actor user = Actor.named("User");
    private String expectedRoomId;

    @When("the user requests reservations for a room")
    public void theUserRequestsReservationsForARoom(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);
        expectedRoomId = data.get("roomId");

        System.out.println("========== ATDD STEP: GET RESERVATIONS BY ROOM ==========");
        System.out.println("Room ID requested: " + expectedRoomId);

        user.attemptsTo(
                GetReservationsByRoom.withId(expectedRoomId)
        );

        System.out.println("Response status: " + SerenityRest.lastResponse().statusCode());
        System.out.println("Response body: " + SerenityRest.lastResponse().asString());
    }

    @Then("the room reservations should be displayed successfully")
    public void theRoomReservationsShouldBeDisplayedSuccessfully() {
        int status = SerenityRest.lastResponse().statusCode();
        String body = SerenityRest.lastResponse().asString();

        System.out.println("Room reservations final status: " + status);
        System.out.println("Room reservations final body: " + body);

        assertEquals(200, status);
        assertNotNull("Response body should not be null", body);
    }

    @And("each reservation should belong to the requested room")
    public void eachReservationShouldBelongToTheRequestedRoom() {

        List<Map<String, Object>> reservations = SerenityRest.lastResponse()
                .jsonPath()
                .getList("$");

        for (Map<String, Object> reservation : reservations) {
            assertEquals(
                    "Reservation should belong to requested room",
                    expectedRoomId,
                    reservation.get("roomId")
            );

            assertNotNull("Guest ID should not be null", reservation.get("guestId"));
            assertNotNull("CheckIn should not be null", reservation.get("checkIn"));
            assertNotNull("CheckOut should not be null", reservation.get("checkOut"));
            assertNotNull("Guests count should not be null", reservation.get("guestsCount"));
        }

        System.out.println("All reservations belong to the requested room");
    }
}