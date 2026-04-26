package bookingya.questions;

import net.serenitybdd.rest.SerenityRest;

public class GuestResponse {

    public static int statusCode() {
        return SerenityRest.lastResponse().statusCode();
    }
}