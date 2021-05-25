package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.AdvertisementService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteAds {

    private final CustomInputStream inputStream = new CustomInputStream();
    private ByteArrayOutputStream outputStream;
    private AdvertisementService sut;
    private final File file = new File("testData");

    //region scenario 2
    @Given("Multiple ads exist")
    public void thereAreAdsAvailable() {
        var ad = createAdInput("1",
                "video",
                "small",
                "example company",
                "1USD",
                "PER_VIEW",
                "http://test.com",
                "title",
                "details");
        var ad2 = createAdInput("2",
                "video",
                "small",
                "example company",
                "1USD",
                "PER_VIEW",
                "http://test.com",
                "title",
                "details");
        createSutWithData(ad);
        inputStream.write(ad2);
        sut.addAdvertisement();
    }

    @When("I delete ad with chosen ID")
    public void iDeleteExistingAdd() {
        inputStream.write("1");
        sut.deleteAdvertisement();
    }

    @Then("This add no longer exists")
    public void thisAddNoLongerExists() {
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).contains("|2|");
        assertThat(output).doesNotContain("|1|");
    }
    //endregion

    // region scenario 3
    @When("I delete ad with ID not in presented ads")
    public void iDeleteNotExistingAdd() {
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).doesNotContain("|3|");
        inputStream.write("3");
        sut.deleteAdvertisement();
    }

    @Then("Nothing is deleted")
    public void nothingIsDeleted() {
        sut.printAdvertisement();
        var output = outputStream.toString();
        assertThat(output).contains("|1|");
        assertThat(output).contains("|2|");
    }
    //endregion

    // region scenario 4
    @Given("There is last ad available")
    public void thereIsLastAdAvailable() {
        var ad = createAdInput("1",
                "video",
                "small",
                "example company",
                "1USD",
                "PER_VIEW",
                "http://test.com",
                "title",
                "details");
        createSutWithData(ad);
    }

    @When("I delete last ad")
    public void iDeleteLastAd() {
        inputStream.write("1");
        sut.deleteAdvertisement();
    }

    @Then("Ads file is not deleted")
    public void adsFileIsNotDeleted() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(true);
    }
    //endregion


    private void createSutWithData(String ad) {
        this.outputStream = new ByteArrayOutputStream();
        inputStream.write(ad);
        this.sut = new AdvertisementConfiguration().create(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
        this.sut.addAdvertisement();
    }

    private String createAdInput(String id,
                                 String type,
                                 String format,
                                 String advertiser,
                                 String price,
                                 String price_type,
                                 String url,
                                 String title,
                                 String details) {
        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                id, type, format, advertiser, price, price_type, url, title, details);
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
