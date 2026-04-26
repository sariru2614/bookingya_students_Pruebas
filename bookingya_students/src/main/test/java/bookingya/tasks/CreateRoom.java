package bookingya.tasks;

import com.project.bookingya.models.Room;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateRoom implements Task {

    private final Room room;

    public CreateRoom(Room room) {
        this.room = room;
    }

    public static CreateRoom withInfo(Room room) {
        return instrumented(CreateRoom.class, room);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri("http://localhost:8080")
                .basePath("/api/room")
                .contentType("application/json")
                .body(room)
                .when()
                .post();
    }
}