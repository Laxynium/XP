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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateAdsFile {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public CreateAdsFile() {
        this.sut = new AdvertisementConfiguration().create(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }


    @Given("There is no ads file")
    public void thereIsNoAdsFile() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(false);
    }

    @When("I add new ad")
    public void iAddNewAd(AdTestItem ad) {
        this.inputStream.write(ad.toStringInput());
        sut.addAdvertisement();
    }

    @Then("Ads file exists")
    public void adsFileExists() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(true);
    }

    @Before
    public void createDir(){
        file.mkdir();
    }

    @After
    public void removeDir(){
        FileSystemUtils.deleteRecursively(file);
    }
}
