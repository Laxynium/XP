package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.util.stream.Collectors;

public class AdvertisementFacade {
    private final AdvertisementsPrinter advertisementsPrinter;
    private final CSVWriter csvWriter;
    private final AdvertisementCreator advertisementCreator;
    private final CSVReader csvReader;
    private final String advertisementsCsvPath;

    public AdvertisementFacade(CSVReader csvReader,
                               CSVWriter csvWriter,
                               AdvertisementsPrinter advertisementsPrinter,
                               AdvertisementCreator advertisementCreator,
                               String advertisementsCsvPath) {
        this.advertisementsPrinter = advertisementsPrinter;
        this.csvWriter = csvWriter;
        this.advertisementCreator = advertisementCreator;
        this.csvReader = csvReader;
        this.advertisementsCsvPath = advertisementsCsvPath;
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
        var onlyOfType = advertisements.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
        advertisementsPrinter.print(onlyOfType);
    }
}
