package features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAndDeleteAd {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");
    private final PrintStream printStream;

    public CreateAndDeleteAd() {
        printStream = new PrintStream(this.outputStream);
        this.sut = new AdvertisementFacade(
                inputStream,
                printStream,
                file.getPath() + "/advertisements.csv");
    }

    void printAdvertisement() {
        outputStream.reset();
        sut.printAdvertisement();
    }

    @Given("There is one ad")
    public void thereIsOneAd(AdTestItem item) {
        String stringInput = item.toStringInput();
        this.inputStream.write(stringInput);
        this.sut.addAdvertisement();

        printAdvertisement();
        var output = outputStream.toString();

        String convertToConsoleForm = item.convertToConsoleForm();
        assertThat(output).contains(convertToConsoleForm);
        outputStream.reset();
    }

    @When("I add new advertisement")
    public void iAddNewAdvertisement(AdTestItem item) {
        this.inputStream.write(item.toStringInput());
        this.sut.addAdvertisement();

        printAdvertisement();
        var output = outputStream.toString();

        assertThat(output).contains(item.convertToConsoleForm());
        outputStream.reset();
    }

    @And("I delete ad with id {word}")
    public void iDeleteAdWithId(String arg0) {
        this.inputStream.write(arg0);
        this.sut.deleteAdvertisement();
    }

    @Then("Ads file has one ad")
    public void adsFileHasOneAd(String expected) {
        printAdvertisement();
        var output = outputStream.toString();

        assertThat(output).isEqualToIgnoringNewLines(expected);
    }

}
