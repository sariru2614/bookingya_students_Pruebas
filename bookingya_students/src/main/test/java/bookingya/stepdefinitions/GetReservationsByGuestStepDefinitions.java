package bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservationsByGuest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static com.project.bookingya.questions.ReservationResponse.body;
import static com.project.bookingya.questions.ReservationResponse.statusCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetReservationsByGuestStepDefinitions {

    private final Actor user = Actor.named("User");

    @When("the user gets reservations by guest id")
    public void theUserGetsReservationsByGuestId(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);
        String guestId = data.get("guestId");

        System.out.println("========== STEP: GET RESERVATIONS BY GUEST ==========");
        System.out.println("Guest ID: " + guestId);

        user.attemptsTo(
                GetReservationsByGuest.withId(guestId)
        );

        System.out.println("Response status: " + statusCode());
        System.out.println("Response body: " + body());
        System.out.println("=====================================================");
    }

    @Then("the reservations by guest should be returned successfully")
    public void theReservationsByGuestShouldBeReturnedSuccessfully() {
        assertEquals(200, statusCode());
        assertFalse("Response should not be empty", body().isEmpty());
    }
}