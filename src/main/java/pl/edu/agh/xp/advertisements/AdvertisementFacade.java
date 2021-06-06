package pl.edu.agh.xp.advertisements;

import lombok.AllArgsConstructor;
import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.csv.FileName;
import pl.edu.agh.xp.advertisements.model.AdvertisementType;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.util.stream.Collectors;

@AllArgsConstructor
public class AdvertisementFacade {
    private final CSVReader csvReader;
    private final CSVWriter csvWriter;
    private final AdvertisementsPrinter advertisementsPrinter;
    private final AdvertisementCreator advertisementCreator;
    private final ConsoleReader consoleReader;
    private final FileName advertisementsCsvPath;

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
}
