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

public class ShowAvailableAds {
    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public ShowAvailableAds() {
        this.sut = new AdvertisementFacade(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }

    @Given("there are ads available")
    public void thereAreAdsAvailable(List<AdTestItem> ads) {
        ads.forEach(ad ->{
            this.inputStream.write(ad.toStringInput());
            this.sut.addAdvertisement();
        });
    }
    @Given("there are no ads available")
    public void thereAreNoAdsAvailable() {
        //Since there are no ads do nothing
    }

    @Given("there is one ad available")
    public void thereIsOneAdAvailable(AdTestItem ad) {
        this.inputStream.write(ad.toStringInput());
        this.sut.addAdvertisement();
    }

    @When("I ask to show in console")
    public void iAskToShowInConsole() {
        sut.printAdvertisement();
    }

    @When("I add new ad and ask to show in console")
    public void iAddNewAdAndAskToShowInConsole(AdTestItem ad) {
        this.inputStream.write(ad.toStringInput());
        sut.addAdvertisement();
        sut.printAdvertisement();
    }

    @Then("I can see them in console")
    public void iCanSeeThemInConsole(String expectedOutput) {
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
        assertThat(output).isNotBlank();
    }

    @Then("I can see zero ads")
    public void iCanSeeZeroAds(String expected) {
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expected);
    }

    @Then("I can see two ads")
    public void iCanSeeTwoAds(String expected) {
        var output = outputStream.toString();
        assertThat(output).isEqualToIgnoringNewLines(expected);
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
