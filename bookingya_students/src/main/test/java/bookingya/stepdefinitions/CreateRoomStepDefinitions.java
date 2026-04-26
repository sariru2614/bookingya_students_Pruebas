package bookingya.stepdefinitions;

import com.project.bookingya.models.Room;
import com.project.bookingya.tasks.CreateRoom;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;

import java.util.Map;

import static com.project.bookingya.questions.RoomResponse.statusCode;
import static org.junit.Assert.assertEquals;

public class CreateRoomStepDefinitions {

    private final Actor user = Actor.named("User");
    private Room room;

    @Given("the user has the room information")
    public void theUserHasTheRoomInformation(DataTable dataTable) {

        Map<String, String> data = dataTable.asMaps().get(0);

        room = new Room(
                data.get("code"),
                data.get("name"),
                data.get("city"),
                Integer.parseInt(data.get("maxGuests")),
                Integer.parseInt(data.get("nightlyPrice")),
                Boolean.parseBoolean(data.get("available"))
        );
    }

    @When("the user creates the room")
    public void theUserCreatesTheRoom() {
        user.attemptsTo(
                CreateRoom.withInfo(room)
        );
    }

    @Then("the room should be created successfully")
    public void theRoomShouldBeCreatedSuccessfully() {
        assertEquals(200, statusCode()); // o 200 según tu API
    }
}