package com.project.bookingya.tasks;

import com.project.bookingya.models.Guest;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateGuest implements Task {

    private final Guest guest;

    public CreateGuest(Guest guest) {
        this.guest = guest;
    }

    public static CreateGuest withInfo(Guest guest) {
        return instrumented(CreateGuest.class, guest);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.given()
                .baseUri("http://localhost:8080")
                .basePath("/api/guest")
                .contentType("application/json")
                .body(guest)
                .when()
                .post();
    }
}