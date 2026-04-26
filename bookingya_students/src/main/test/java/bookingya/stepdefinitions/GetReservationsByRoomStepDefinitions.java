package bookingya.stepdefinitions;

import com.project.bookingya.tasks.GetReservationsByRoom;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static com.project.bookingya.questions.ReservationsByRoomResponse.body;
import static com.project.bookingya.questions.ReservationsByRoomResponse.statusCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetReservationsByRoomStepDefinitions {

    private final Actor user = Actor.named("User");

    @When("the user gets reservations by room id")
    public void theUserGetsReservationsByRoomId(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);
        String roomId = data.get("roomId");

        System.out.println("========== STEP: GET RESERVATIONS BY ROOM ==========");
        System.out.println("Room ID: " + roomId);

        user.attemptsTo(
                GetReservationsByRoom.withId(roomId)
        );

        System.out.println("Response status: " + statusCode());
        System.out.println("Response body: " + body());
        System.out.println("====================================================");
    }

    @Then("the reservations by room should be returned successfully")
    public void theReservationsByRoomShouldBeReturnedSuccessfully() {
        assertEquals(200, statusCode());
        assertFalse("Response should not be empty", body().isEmpty());
    }
}