package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddOneAdvertisementToEmptyCollection {

    private ByteArrayOutputStream outputStream;

    private AdvertisementFacade sut;

    private final File file = new File("testData");

    @Before
    public void createDir() {
        file.mkdir();
    }

    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }

    @Given("there are no ads available")
    public void thereAreNoAdsAvailable() {
        var ad = createAdInput("1",
                "video",
                "small",
                "example company",
                "1USD",
                "PER_VIEW",
                "http://test.com",
                "title",
                "details");

        this.outputStream = new ByteArrayOutputStream();
        this.sut = new AdvertisementConfiguration().create(
                new ByteArrayInputStream(ad.getBytes()),
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv"
        );

        assertEquals("", this.outputStream.toString());
    }

    @When("I add one advertisement")
    public void iAddOneAdvertisement() {
        sut.addAdvertisement();

        sut.printAdvertisement();
    }

    @Then("I can see one advertisement")
    public void iCanSeeOneAdvertisement() {
        var output = this.outputStream.toString();
        assertNotEquals("", output);
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

}
