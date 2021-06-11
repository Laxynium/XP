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
    private final PrintStream printStream;

    public AddOneAdvertisementToEmptyCollection() {
        printStream = new PrintStream(this.outputStream);
        this.sut = new AdvertisementFacade(
                inputStream,
                printStream,
                file.getPath() + "/advertisements.csv");
    }

    void printAdvertisement() {
        outputStream.reset();
        printStream.flush();
        sut.printAdvertisement();
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
        printAdvertisement();
        var output = outputStream.toString();
        outputStream.reset();
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
    }

    @When("I add one advertisement")
    public void iAddOneAdvertisement(AdTestItem ad) {
        this.inputStream.write(ad.toStringInput());
        sut.addAdvertisement();
        printAdvertisement();
    }

    @Then("I can see one advertisement")
    public void iCanSeeOneAdvertisement(String expectedOutput) {
        outputStream.reset();
        printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
    }

}