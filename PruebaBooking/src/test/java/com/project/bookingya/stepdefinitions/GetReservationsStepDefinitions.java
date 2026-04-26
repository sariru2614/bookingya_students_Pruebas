package com.project.bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservations;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static com.project.bookingya.questions.ReservationResponse.body;
import static com.project.bookingya.questions.ReservationResponse.statusCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetReservationsStepDefinitions {

    private final Actor user = Actor.named("User");

    @When("the user gets all reservations")
    public void theUserGetsAllReservations() {

        System.out.println("\n===== API: GET ALL RESERVATIONS =====");
        System.out.println("Request: GET http://localhost:8080/api/reservation");

        user.attemptsTo(
                GetReservations.fromApi()
        );

        System.out.println("Status: " + statusCode());
        System.out.println("Response: " + body());
        System.out.println("=====================================\n");
    }

    @Then("the reservations should be returned successfully")
    public void theReservationsShouldBeReturnedSuccessfully() {
        assertEquals(200, statusCode());
        assertFalse("The response body should not be empty", body().isEmpty());

        System.out.println("Validation: reservations were returned successfully");
    }
}