package features;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.xp.advertisements.AdvertisementFacade;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateConfigurationFile {

    private final InputStreamFake inputStream = new InputStreamFake();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final AdvertisementFacade sut;
    private final File file = new File("testData");

    public CreateConfigurationFile() {
        this.sut = new AdvertisementFacade(
                inputStream,
                new PrintStream(this.outputStream),
                file.getPath() + "/advertisements.csv");
    }



    @Given("There is no configuration file")
    public void thereIsNoConfigurationFile() {
        var configurationFile = new File(file.getPath() + "/configuration.json");
        assertThat(configurationFile.exists()).isEqualTo(false);
    }

    @When("I generate configuration")
    public void iGenerateConfiguration() {
        inputStream.write(file.getPath());
        sut.generateConfiguration();
    }

    @Then("Configuration file exists")
    public void configurationFileExists(){
        var configurationFile = new File(file.getPath() + "/configuration.json");
        assertThat(configurationFile.exists()).isEqualTo(true);
    }

    @And("Has default configuration")
    public void hasDefaultConfiguration(String expectedOutput) throws IOException {
        var configurationFile = new File(file.getPath() + "/configuration.json");
        var output = new String(new FileInputStream(configurationFile).readAllBytes());
        Assertions.assertThat(output).isEqualToIgnoringNewLines(expectedOutput);
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
