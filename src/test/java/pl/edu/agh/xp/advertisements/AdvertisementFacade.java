package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.configuration.ConfigurationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class AdvertisementFacade {

    private final ConsoleReader consoleReader;
    private final AdvertisementService advertisementService;
    private final ConfigurationService configurationService;

    public AdvertisementFacade(InputStream inputStream, PrintStream printStream, String advertisementCsvPath) {
        var reader = new ConsoleReader(inputStream, printStream);
        advertisementService = new AdvertisementService(reader, printStream, FileName.create(advertisementCsvPath));
        consoleReader = reader;
        configurationService = new ConfigurationService();
    }

    public void addAdvertisement() {
        advertisementService.addAdvertisement();
    }

    public void printAdvertisement() {
        advertisementService.printAdvertisement();
    }

    public void printAdvertisementWithType() {
        var types = Arrays.toString(AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.toArray());
        var type = consoleReader.readString("Write advertisement type and press Enter. Available types are: " + types);
        advertisementService.printAdvertisementWithType(type);
    }

    public void deleteAdvertisement() {
        var id = consoleReader.readInteger("Please enter id od advertisement to delete:");
        advertisementService.deleteAdvertisement(id);
    }

    public void generateAdvertisementCsvFile() {
        var path = consoleReader.readString("In which directory do you want to save csv file?\nPlease enter relative path: ") + "/urls.csv";
        advertisementService.generateAdvertisementsCsvFile(path);
    }

    public void generateConfiguration() {
        configurationService.save(consoleReader.readString("In which directory do you want to save configuration?\nPlease enter relative path: ") + "/configuration.json");
    }

    public void readConfiguration() {
        configurationService.readConfiguration();
        advertisementService.setAdvertisementsCsvPath(AdvertisementConfiguration.INSTANCE.pathToAdvertisements);
    }

}
