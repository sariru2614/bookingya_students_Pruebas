package bookingya.questions;

import net.serenitybdd.rest.SerenityRest;

public class RoomResponse {

    public static int statusCode() {
        return SerenityRest.lastResponse().statusCode();
    }
}