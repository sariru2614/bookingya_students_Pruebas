package com.project.bookingya.stepdefinitions;

import com.project.bookingya.models.Guest;
import com.project.bookingya.tasks.CreateGuest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static com.project.bookingya.questions.ReservationResponse.statusCode;
import static org.junit.Assert.assertEquals;

public class CreateGuestStepDefinitions {

    private final Actor user = Actor.named("User");
    private Guest guest;

    @Given("the user has the guest information")
    public void theUserHasTheGuestInformation(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        guest = new Guest(
                data.get("identification"),
                data.get("name"),
                data.get("email")
        );
    }

    @When("the user creates the guest")
    public void theUserCreatesTheGuest() {
        user.attemptsTo(
                CreateGuest.withInfo(guest)
        );
    }

    @Then("the guest should be created successfully")
    public void theGuestShouldBeCreatedSuccessfully() {
        assertEquals(200, statusCode());
    }
}