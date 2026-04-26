package com.project.bookingya.stepdefinitions;

import com.project.bookingya.models.Guest;
import com.project.bookingya.models.Reservation;
import com.project.bookingya.models.Room;
import com.project.bookingya.tasks.CreateGuest;
import com.project.bookingya.tasks.CreateReservation;
import com.project.bookingya.tasks.CreateRoom;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static org.junit.Assert.*;

public class AtddCreateReservationStepDefinitions {

    private final Actor guestActor = Actor.named("Guest");

    private String guestId;
    private String roomId;

    private String expectedCheckIn;
    private String expectedCheckOut;
    private int expectedGuestsCount;
    private String expectedNotes;

    @Given("the guest is registered for booking")
    public void theGuestIsRegisteredForBooking(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        String identification = System.getProperty("atddGuestIdentification", data.get("identification"));
        String name = System.getProperty("atddGuestName", data.get("name"));
        String email = System.getProperty("atddGuestEmail", data.get("email"));

        Guest guest = new Guest(identification, name, email);

        System.out.println("========== ATDD STEP 1: REGISTER GUEST ==========");
        System.out.println("Guest identification: " + identification);
        System.out.println("Guest name: " + name);
        System.out.println("Guest email: " + email);

        guestActor.attemptsTo(CreateGuest.withInfo(guest));

        int status = SerenityRest.lastResponse().statusCode();
        String response = SerenityRest.lastResponse().asString();

        System.out.println("Guest response status: " + status);
        System.out.println("Guest response body: " + response);

        assertEquals(200, status);

        guestId = SerenityRest.lastResponse().jsonPath().getString("id");

        assertNotNull("Guest ID should not be null", guestId);

        System.out.println("Guest created with ID: " + guestId);
    }

    @And("the guest selects an available room")
    public void theGuestSelectsAnAvailableRoom(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        String code = System.getProperty("atddRoomCode", data.get("code"));
        String name = System.getProperty("atddRoomName", data.get("name"));

        Room room = new Room(
                code,
                name,
                data.get("city"),
                Integer.parseInt(data.get("maxGuests")),
                Integer.parseInt(data.get("nightlyPrice")),
                Boolean.parseBoolean(data.get("available"))
        );

        System.out.println("========== ATDD STEP 2: SELECT AVAILABLE ROOM ==========");
        System.out.println("Room code: " + code);
        System.out.println("Room name: " + name);

        guestActor.attemptsTo(CreateRoom.withInfo(room));

        int status = SerenityRest.lastResponse().statusCode();
        String response = SerenityRest.lastResponse().asString();

        System.out.println("Room response status: " + status);
        System.out.println("Room response body: " + response);

        assertEquals(200, status);

        roomId = SerenityRest.lastResponse().jsonPath().getString("id");

        assertNotNull("Room ID should not be null", roomId);

        System.out.println("Room created with ID: " + roomId);
    }

    @When("the guest confirms the booking")
    public void theGuestConfirmsTheBooking(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        expectedCheckIn = data.get("checkIn");
        expectedCheckOut = data.get("checkOut");
        expectedGuestsCount = Integer.parseInt(data.get("guestsCount"));
        expectedNotes = data.get("notes");

        Reservation reservation = new Reservation(
                guestId,
                roomId,
                expectedCheckIn,
                expectedCheckOut,
                expectedGuestsCount,
                expectedNotes
        );

        System.out.println("========== ATDD STEP 3: CONFIRM BOOKING ==========");
        System.out.println("Guest ID sent: " + guestId);
        System.out.println("Room ID sent: " + roomId);
        System.out.println("CheckIn sent: " + expectedCheckIn);
        System.out.println("CheckOut sent: " + expectedCheckOut);

        guestActor.attemptsTo(CreateReservation.withInfo(reservation));

        System.out.println("Reservation response status: " + SerenityRest.lastResponse().statusCode());
        System.out.println("Reservation response body: " + SerenityRest.lastResponse().asString());
    }

    @Then("the booking should be confirmed")
    public void theBookingShouldBeConfirmed() {

        int status = SerenityRest.lastResponse().statusCode();
        String body = SerenityRest.lastResponse().asString();

        System.out.println("========== ATDD FINAL ASSERT ==========");
        System.out.println("Final booking status code: " + status);
        System.out.println("Final booking response body: " + body);
        System.out.println("=======================================");

        assertEquals(200, status);

        String responseGuestId = SerenityRest.lastResponse().jsonPath().getString("guestId");
        String responseRoomId = SerenityRest.lastResponse().jsonPath().getString("roomId");

        assertNotNull("Guest ID should not be null", responseGuestId);
        assertNotNull("Room ID should not be null", responseRoomId);

        System.out.println("Booking confirmed successfully");
    }

    @And("the booking details should match the guest request")
    public void theBookingDetailsShouldMatchTheGuestRequest() {

        String responseGuestId = SerenityRest.lastResponse().jsonPath().getString("guestId");
        String responseRoomId = SerenityRest.lastResponse().jsonPath().getString("roomId");
        String responseCheckIn = SerenityRest.lastResponse().jsonPath().getString("checkIn");
        String responseCheckOut = SerenityRest.lastResponse().jsonPath().getString("checkOut");
        int responseGuestsCount = SerenityRest.lastResponse().jsonPath().getInt("guestsCount");
        String responseNotes = SerenityRest.lastResponse().jsonPath().getString("notes");

        assertEquals("Guest ID should match", guestId, responseGuestId);
        assertEquals("Room ID should match", roomId, responseRoomId);

        assertEquals("CheckIn should match",
                normalizeDate(expectedCheckIn),
                normalizeDate(responseCheckIn));

        assertEquals("CheckOut should match",
                normalizeDate(expectedCheckOut),
                normalizeDate(responseCheckOut));

        assertEquals("Guests count should match", expectedGuestsCount, responseGuestsCount);
        assertEquals("Notes should match", expectedNotes, responseNotes);

        System.out.println("Booking details match the guest request");
    }

    private String normalizeDate(String date) {
        return date
                .replace(".000Z", "")
                .replace("Z", "");
    }
}