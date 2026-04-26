package bookingya.tasks;

import com.project.bookingya.models.Reservation;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateReservation implements Task {

    private final Reservation reservation;

    public CreateReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public static CreateReservation withInfo(Reservation reservation) {
        return instrumented(CreateReservation.class, reservation);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri("http://localhost:8080")
                .basePath("/api/reservation")
                .contentType("application/json")
                .body(reservation)
                .when()
                .post();
    }
}