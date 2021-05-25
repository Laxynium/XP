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
import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateAdsFile {

    private AdvertisementFacade sut;

    @Given("There is no ads file")
    public void thereIsNoAdsFile() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(false);
    }

    @When("I add new ad")
    public void iAddNewAd() {
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

    @Then("Ads file exists")
    public void adsFileExists() {
        var adsFile = new File(file.getPath() + "/advertisements.csv");
        assertThat(adsFile.exists()).isEqualTo(true);
    }

    private void createSutWithData(String ad){
        this.sut = new AdvertisementConfiguration().create(
                new ByteArrayInputStream(ad.getBytes()),
                System.out,
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

    private final File file = new File("testData");

    @Before
    public void createDir(){
        file.mkdir();
    }

    @After
    public void removeDir(){
        FileSystemUtils.deleteRecursively(file);
    }
}
