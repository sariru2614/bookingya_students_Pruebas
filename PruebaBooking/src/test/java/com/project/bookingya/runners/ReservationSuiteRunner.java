package com.project.bookingya.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/create_reservation.feature",
                "src/test/resources/features/get_reservations.feature",
                "src/test/resources/features/get_reservationsbyguest.feature",
                "src/test/resources/features/get_reservationsbyroom.feature"
        },
        glue = "com.project.bookingya.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class ReservationSuiteRunner {
}