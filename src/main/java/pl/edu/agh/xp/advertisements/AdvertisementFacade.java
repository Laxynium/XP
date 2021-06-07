package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.configuration.ConfigurationService;
import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.csv.FileName;
import pl.edu.agh.xp.advertisements.model.AdvertisementType;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

public class AdvertisementFacade {

    private final CSVReader csvReader;
    private final CSVWriter csvWriter;
    private final AdvertisementsPrinter advertisementsPrinter;
    private final AdvertisementCreator advertisementCreator;
    private final ConsoleReader consoleReader;
    private FileName advertisementsCsvPath;
    private final ConfigurationService configurationService;

    public AdvertisementFacade(InputStream inputStream, PrintStream printStream, String advertisementCsvPath) {
        var reader = new ConsoleReader(inputStream);
        csvReader = new CSVReader();
        csvWriter = new CSVWriter();
        advertisementsPrinter = new AdvertisementsPrinter(printStream);
        advertisementCreator = new AdvertisementCreator(reader);
        consoleReader = reader;
        advertisementsCsvPath = FileName.create(advertisementCsvPath);
        configurationService = new ConfigurationService();
    }

    public void addAdvertisement() {
        var ad = advertisementCreator.createFromConsole();

        csvWriter.write(advertisementsCsvPath, ad);
    }

    public void printAdvertisement() {
        var advertisements = csvReader.read(advertisementsCsvPath);
        advertisementsPrinter.print(advertisements);
    }

    public void printAdvertisementWithType(String type) {
        var advertisements = csvReader.read(advertisementsCsvPath);
        var onlyOfType = advertisements.stream().filter(x -> x.getType().equals(AdvertisementType.create(type)))
                .collect(Collectors.toList());
        advertisementsPrinter.print(onlyOfType);
    }

    public void deleteAdvertisement() {
        var id = consoleReader.readInteger("Please enter id od advertisement to delete:");
        var advertisements = csvReader.read(advertisementsCsvPath)
                .stream()
                .filter(it -> !it.getId().equals(id))
                .collect(Collectors.toList());
        csvWriter.delete(advertisementsCsvPath);
        advertisements.forEach(it -> csvWriter.write(advertisementsCsvPath, it));
    }

    public void generateConfiguration() {
        configurationService.save(consoleReader.readString("In which directory do you want to save configuration?\nPlease enter relative path: ") + "/configuration.json");
    }

    public void readConfiguration() {
        configurationService.read();

        String newPath = AdvertisementConfiguration.INSTANCE.pathToAdvertisements;
        if (!advertisementsCsvPath.getValue().equals(newPath)) {
            advertisementsCsvPath = FileName.create(newPath);
        }
    }

}
