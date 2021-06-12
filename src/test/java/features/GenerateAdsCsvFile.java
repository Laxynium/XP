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
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GenerateAdsCsvFile {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");
    private final PrintStream printStream;

    public GenerateAdsCsvFile() {
        printStream = new PrintStream(this.outputStream);
        this.sut = new AdvertisementFacade(
                inputStream,
                printStream,
                file.getPath() + "/advertisements.csv");
    }

    @Given("Two ads exist")
    public void twoAdsExist(List<AdTestItem> ads) {
        ads.forEach(ad -> {
            this.inputStream.write(ad.toStringInput());
            this.sut.addAdvertisement();
        });
    }

    @When("I generate advertisements csv file")
    public void iGenerateAdvertisementsCsvFile() {
        inputStream.write("testData");
        sut.generateAdvertisementCsvFile();
    }

    @Then("Csv file is created and contains two ads")
    public void csvFileIsCreatedAndContainsTwoAds(String expected) throws IOException {
        var csvFile = new File(file.getPath() + "/urls.csv");
        assertThat(csvFile).exists();
        assertThat(Files.readString(csvFile.toPath())).isEqualToIgnoringNewLines(expected);
    }

    @Before
    public void createDir() {
        file.mkdir();
    }

    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }

}
