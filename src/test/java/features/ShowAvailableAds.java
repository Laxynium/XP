package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.*;

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

    @Given("there are no ads available")
    public void thereAreNoAdsAvailable() {
        try {
            createEmptySut();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("there is one ad available")
    public void thereIsOneAdAvailable() {
        var ad = createDualAdInput("1",
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

    @When("I add new ad and ask to show in console")
    public void iAddNewAdAndAskToShowInConsole() {
        sut.addAdvertisement();
        sut.printAdvertisement();
    }

    @Then("I can see them in console")
    public void iCanSeeThemInConsole() {
        var output = outputStream.toString();
        assertThat(output).isNotBlank();
    }

    @Then("I can see zero ads")
    public void iCanSeeZeroAds() {

        var expected = """
                |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
                End of Advertisements
                """;
        var output = outputStream.toString();
        assertThat(output).isEqualTo(expected);
    }

    @Then("I can see two ads")
    public void iCanSeeTwoAds() {
        var expected = "|ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|\n" +
                "|1|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|\n" +
                "|12|video|small|example company|1USD|PER_VIEW|http://test.com|title|details|\n" +
                "End of Advertisements\n";
        var output = outputStream.toString();
        assertThat(output).isEqualTo(expected);
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

    private void createEmptySut() throws IOException {
        this.outputStream = new ByteArrayOutputStream();
        String advertisementCsvPath = file.getPath() + "/advertisements.csv";
        boolean exists = this.file.exists();
        var injectedEmptyFile = new File(advertisementCsvPath);
        var createdFile = injectedEmptyFile.createNewFile();
        this.sut = new AdvertisementConfiguration().create(
                new ByteArrayInputStream("".getBytes()),
                new PrintStream(this.outputStream),
                advertisementCsvPath);

    }

    private String createDualAdInput(String s, String video, String small, String example_company, String s1, String per_view, String s2, String title, String details) {
        var firstAdd = this.createAdInput(s, video, small, example_company, s1, per_view, s2, title, details);
        var secondAdd = this.createAdInput(s+"2", video, small, example_company, s1, per_view, s2, title, details);
        return firstAdd + secondAdd;
    }

}
