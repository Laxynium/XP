package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.JSONObject;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoadDefaultConfiguration {
    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public LoadDefaultConfiguration() {
        this.sut = new AdvertisementFacade(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }

    @Before
    public void createDir() {
        file.mkdir();
    }

    @After
    public void removeDir() {
        FileSystemUtils.deleteRecursively(file);
    }

    @Then("Default pathToAdvertisements is loaded")
    public void defaultPathToAdvertisementsIsLoaded(String pathToAdvertisements) {
        assertThat(AdvertisementConfiguration.INSTANCE.pathToAdvertisements)
                .isEqualTo(pathToAdvertisements);
    }

    @And("Default availableAdvertisementTypes is loaded")
    public void defaultAvailableAdvertisementTypesIsLoaded(List<String> availableAdvertisementTypes) {
        assertThat(AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes)
                .isEqualTo(availableAdvertisementTypes);
    }

    @And("Default availableAdvertisementFormats is loaded")
    public void defaultAvailableAdvertisementFormatsIsLoaded(List<String> availableAdvertisementFormats) {
        assertThat(AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats)
                .isEqualTo(availableAdvertisementFormats);
    }

    @And("Default availableCurrencies is loaded")
    public void defaultAvailableCurrenciesIsLoaded(List<String> availableCurrencies) {
        assertThat(AdvertisementConfiguration.INSTANCE.availableCurrencies)
                .isEqualTo(availableCurrencies);
    }

    @And("Default availablePricingMethods is loaded")
    public void defaultAvailablePricingMethodsIsLoaded(List<String> availablePricingMethods) {
        assertThat(AdvertisementConfiguration.INSTANCE.availablePricingMethods)
                .isEqualTo(availablePricingMethods);
    }
}
