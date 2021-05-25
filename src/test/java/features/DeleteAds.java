package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteAds {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public DeleteAds() {
        this.sut = new AdvertisementConfiguration().create(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }

    //region scenario 2
    @Given("Multiple ads exist")
    public void thereAreAdsAvailable(List<AdTestItem> ads) {
        ads.forEach(ad -> {
            this.inputStream.write(ad.toStringInput());
            this.sut.addAdvertisement();
        });
    }

    @When("I delete ad with chosen ID")
    public void iDeleteExistingAdd(String id) {
        inputStream.write(id);
        sut.deleteAdvertisement();
    }

    @Then("This add no longer exists")
    public void thisAddNoLongerExists(String expected) {
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expected);
    }
    //endregion

    // region scenario 3
    @When("I delete ad with ID not in presented ads")
    public void iDeleteNotExistingAdd(String id) {
        sut.printAdvertisement();
        var output = outputStream.toString();
        outputStream.reset();
        assertThat(output).doesNotContain("|" + id + "|");
        inputStream.write(id);
        sut.deleteAdvertisement();
    }

    @Then("Nothing is deleted")
    public void nothingIsDeleted(String expected) {
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expected);
    }
    //endregion

    // region scenario 4
    @Given("There is last ad available")
    public void thereIsLastAdAvailable(List<AdTestItem> ads) {
        ads.forEach(ad -> {
            this.inputStream.write(ad.toStringInput());
            this.sut.addAdvertisement();
        });
    }

    @When("I delete last ad")
    public void iDeleteLastAd(String id) {
        inputStream.write(id);
        sut.deleteAdvertisement();
    }

    @Then("Ads file is not deleted")
    public void adsFileIsNotDeleted() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(true);
    }
    //endregion

    @Before
    public void createDir() {
        file.mkdir();
    }

    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }
}
