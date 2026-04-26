package bookingya.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetReservationsByGuest implements Task {

    private final String guestId;

    public GetReservationsByGuest(String guestId) {
        this.guestId = guestId;
    }

    public static GetReservationsByGuest withId(String guestId) {
        return instrumented(GetReservationsByGuest.class, guestId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri("http://localhost:8080")
                .basePath("/api/reservation/guest/" + guestId)
                .when()
                .get();
    }
}