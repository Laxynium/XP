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

public class ShowAvailableAds {
    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;

    public ShowAvailableAds() {
        this.sut = new AdvertisementConfiguration().create(
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
    @When("I ask to show in console")
    public void iAskToShowInConsole() {
        sut.printAdvertisement();
    }
    @Then("I can see them in console")
    public void iCanSeeThemInConsole(String expectedOutput) {
        var output = outputStream.toString();
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
