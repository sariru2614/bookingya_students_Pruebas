package com.project.bookingya.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetReservationsByRoom implements Task {

    private final String roomId;

    public GetReservationsByRoom(String roomId) {
        this.roomId = roomId;
    }

    public static GetReservationsByRoom withId(String roomId) {
        return instrumented(GetReservationsByRoom.class, roomId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri("http://localhost:8080")
                .basePath("/api/reservation/room/" + roomId)
                .when()
                .get();
    }
}