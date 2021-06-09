package pl.edu.agh.xp.advertisements.service.advertisement;

import pl.edu.agh.xp.advertisements.model.AdvertisementType;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.service.csv.CSVReader;
import pl.edu.agh.xp.advertisements.service.csv.CSVWriter;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

import java.io.PrintStream;
import java.util.stream.Collectors;

public class AdvertisementService {

    private final CSVReader csvReader;
    private final CSVWriter csvWriter;
    private final AdvertisementsPrinter advertisementsPrinter;
    private final AdvertisementCreator advertisementCreator;
    private FileName advertisementsCsvPath;

    public AdvertisementService(ConsoleReader reader, PrintStream printStream, FileName fileName) {
        csvReader = new CSVReader();
        csvWriter = new CSVWriter();
        advertisementsPrinter = new AdvertisementsPrinter(printStream);
        advertisementCreator = new AdvertisementCreator(reader);
        advertisementsCsvPath = fileName;
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

    public void deleteAdvertisement(Integer id) {
        var advertisements = csvReader.read(advertisementsCsvPath)
                .stream()
                .filter(it -> !it.getId().equals(id))
                .collect(Collectors.toList());
        csvWriter.delete(advertisementsCsvPath);
        advertisements.forEach(it -> csvWriter.write(advertisementsCsvPath, it));
    }

    public void setAdvertisementsCsvPath(String newPath) {
        if (!advertisementsCsvPath.getValue().equals(newPath)) {
            advertisementsCsvPath = FileName.create(newPath);
        }
    }
}
