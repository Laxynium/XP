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

import static org.assertj.core.api.Assertions.assertThat;

public class ShowAvailableAds {
    
    private ByteArrayOutputStream outputStream;
    
    private AdvertisementFacade sut;
   
    @Given("there are ads available")
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
        createSutWithData(ad);
    }

    @When("I ask to show in console")
    public void iAskToShowInConsole() {
        sut.printAdvertisement();
    }

    @Then("I can see them in console")
    public void iCanSeeThemInConsole() {
        var output = outputStream.toString();
        assertThat(output).isNotBlank();
    }

    private void createSutWithData(String ad){
        this.outputStream = new ByteArrayOutputStream();
        this.sut = new AdvertisementConfiguration().create(
                new ByteArrayInputStream(ad.getBytes()),
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
