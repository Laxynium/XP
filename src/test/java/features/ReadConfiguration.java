package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import java.io.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadConfiguration {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");
    private JSONObject json;

    public ReadConfiguration() {
        this.sut = new AdvertisementFacade(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }

    @Given("There is a configuration file")
    public void thereIsAConfigurationFile(String jsonString) throws JSONException, IOException {
        json = new JSONObject(jsonString);
        FileWriter fileWriter = new FileWriter(file.getPath() + "/configuration.json");
        fileWriter.write(json.toString());
        fileWriter.close();

        var configurationFile = new File(file.getPath() + "/configuration.json");
        assertThat(configurationFile.exists()).isEqualTo(true);
    }


    @When("I start the program")
    public void iStartTheProgram() {
        sut.readConfiguration(file.getPath() + "/configuration.json");

    }

    @Then("Parameter values are loaded")
    public void parameterValuesAreLoaded() throws JSONException {
        assertThat(AdvertisementConfiguration.INSTANCE.pathToAdvertisements)
                .isEqualTo(json.getString("pathToAdvertisements"));
        assertThat(AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.toString())
                .isEqualTo(json.getString("availableAdvertisementTypes")
                        .replace("\"","").replace(",", ", "));
        assertThat(AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats.toString())
                .isEqualTo(json.getString("availableAdvertisementFormats")
                        .replace("\"","").replace(",", ", "));
        assertThat(AdvertisementConfiguration.INSTANCE.availableCurrencies.toString())
                .isEqualTo(json.getString("availableCurrencies")
                        .replace("\"","").replace(",", ", "));
        assertThat(AdvertisementConfiguration.INSTANCE.availablePricingMethods.toString())
                .isEqualTo(json.getString("availablePricingMethods")
                        .replace("\"","").replace(",", ", "));
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
