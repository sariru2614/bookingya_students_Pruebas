package bookingya.questions;

import net.serenitybdd.rest.SerenityRest;

public class ReservationResponse {

    public static int statusCode() {
        return SerenityRest.lastResponse().statusCode();
    }
    public static String body() {
        return SerenityRest.lastResponse().asString();
    }
}