package features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.*;

public class ShowAvailableAds {
    @Given("there are ads available")
    public void thereAreAdsAvailable() {
    }


    @When("I ask to show in console")
    public void iAskToShowInConsole() {

    }

    @Then("I can see them in console")
    public void iCanSeeThemInConsole() {
        assertThat(1).isEqualTo(1);
    }
}
