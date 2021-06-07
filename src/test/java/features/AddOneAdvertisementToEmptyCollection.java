package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AddOneAdvertisementToEmptyCollection {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public AddOneAdvertisementToEmptyCollection() {
        this.sut = new AdvertisementFacade(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }

    @Before
    public void createDir() {
        file.mkdir();
    }

    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }

    @Given("there are zero ads available")
    public void thereAreNoAdsAvailable(String expectedOutput) {
        sut.printAdvertisement();
        var output = outputStream.toString();
        outputStream.reset();
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
    }

    @When("I add one advertisement")
    public void iAddOneAdvertisement(AdTestItem ad) {
        this.inputStream.write(ad.toStringInput());
        sut.addAdvertisement();
        sut.printAdvertisement();
    }

    @Then("I can see one advertisement")
    public void iCanSeeOneAdvertisement(String expectedOutput) {
        outputStream.reset();
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
    }

}