package bookingya.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/create_guest.feature",
        glue = "com.project.bookingya.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class CreateGuestRunner {
}