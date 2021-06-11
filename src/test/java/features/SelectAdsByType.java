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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectAdsByType {
    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final PrintStream printStream;

    public SelectAdsByType() {
        printStream = new PrintStream(this.outputStream);
        this.sut = new AdvertisementFacade(
                inputStream,
                printStream,
                file.getPath() + "/advertisements.csv");
    }

    void printAdvertisementWithType() {
        outputStream.reset();
        sut.printAdvertisementWithType();
    }

    @Given("there are following ads")
    public void thereAreAdsWithDifferentTypes(List<AdTestItem> ads) {
        ads.forEach(ad ->{
            this.inputStream.write(ad.toStringInput());
            this.sut.addAdvertisement();
        });
    }

    @When("I ask for ads of type {word}")
    public void iAskForAdsOfTypeType(String type) {
        this.inputStream.write(type);
        printAdvertisementWithType();
    }

    @Then("only ads with selected type are returned")
    public void onlyAdsWithSelectedTypeAreReturned(String expectedOutput) {
        var output = outputStream.toString();
        output = output.substring(output.indexOf('\n')+1);
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
    }

    private final File file = new File("testData");
    @Before
    public void createDir() {
        file.mkdir();
    }
    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }
}
