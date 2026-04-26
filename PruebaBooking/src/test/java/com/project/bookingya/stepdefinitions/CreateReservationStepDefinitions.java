package com.project.bookingya.stepdefinitions;

import com.project.bookingya.models.Guest;
import com.project.bookingya.models.Room;
import com.project.bookingya.models.Reservation;
import com.project.bookingya.tasks.CreateGuest;
import com.project.bookingya.tasks.CreateRoom;
import com.project.bookingya.tasks.CreateReservation;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static com.project.bookingya.questions.ReservationResponse.statusCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateReservationStepDefinitions {

    private final Actor user = Actor.named("User");

    private String guestId;
    private String roomId;

    @Given("the user has a created guest")
    public void theUserHasACreatedGuest(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        String identification = System.getProperty("guestIdentification", data.get("identification"));
        String name = System.getProperty("guestName", data.get("name"));
        String email = System.getProperty("guestEmail", data.get("email"));

        Guest guest = new Guest(
                identification,
                name,
                email
        );

        System.out.println("========== STEP 1: CREATE GUEST ==========");
        System.out.println("Creating guest with identification: " + identification);
        System.out.println("Creating guest with name: " + name);
        System.out.println("Creating guest with email: " + email);

        user.attemptsTo(CreateGuest.withInfo(guest));

        int status = SerenityRest.lastResponse().statusCode();
        String response = SerenityRest.lastResponse().asString();

        System.out.println("Guest response status: " + status);
        System.out.println("Guest response body: " + response);

        assertEquals(200, status);

        guestId = SerenityRest.lastResponse().jsonPath().getString("id");

        System.out.println("Guest created with ID: " + guestId);
        System.out.println("==========================================");

        assertNotNull("The guest id should not be null", guestId);
    }

    @And("the user has a created room")
    public void theUserHasACreatedRoom(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        String code = System.getProperty("roomCode", data.get("code"));
        String name = System.getProperty("roomName", data.get("name"));

        Room room = new Room(
                code,
                name,
                data.get("city"),
                Integer.parseInt(data.get("maxGuests")),
                Integer.parseInt(data.get("nightlyPrice")),
                Boolean.parseBoolean(data.get("available"))
        );

        System.out.println("========== STEP 2: CREATE ROOM ==========");
        System.out.println("Creating room with code: " + code);
        System.out.println("Creating room with name: " + name);

        user.attemptsTo(CreateRoom.withInfo(room));

        int status = SerenityRest.lastResponse().statusCode();
        String response = SerenityRest.lastResponse().asString();

        System.out.println("Room response status: " + status);
        System.out.println("Room response body: " + response);

        assertEquals(200, status);

        roomId = SerenityRest.lastResponse().jsonPath().getString("id");

        System.out.println("Room created with ID: " + roomId);
        System.out.println("=========================================");

        assertNotNull("The room id should not be null", roomId);
    }

    @When("the user creates the reservation")
    public void theUserCreatesTheReservation(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        Reservation reservation = new Reservation(
                guestId,
                roomId,
                data.get("checkIn"),
                data.get("checkOut"),
                Integer.parseInt(data.get("guestsCount")),
                data.get("notes")
        );

        System.out.println("========== STEP 3: CREATE RESERVATION ==========");
        System.out.println("Creating reservation with guestId: " + guestId);
        System.out.println("Creating reservation with roomId: " + roomId);
        System.out.println("CheckIn: " + data.get("checkIn"));
        System.out.println("CheckOut: " + data.get("checkOut"));

        user.attemptsTo(CreateReservation.withInfo(reservation));

        int status = SerenityRest.lastResponse().statusCode();
        String response = SerenityRest.lastResponse().asString();

        System.out.println("Reservation response status: " + status);
        System.out.println("Reservation response body: " + response);
        System.out.println("================================================");
    }

    @Then("the reservation should be created successfully")
    public void theReservationShouldBeCreatedSuccessfully() {

        int status = SerenityRest.lastResponse().statusCode();
        String body = SerenityRest.lastResponse().asString();

        System.out.println("========== FINAL ASSERT RESERVATION ==========");
        System.out.println("Final reservation status code: " + status);
        System.out.println("Final reservation response body: " + body);
        System.out.println("==============================================");

        assertEquals(200, statusCode());
        System.out.println("Reservation created successfully");
    }
}