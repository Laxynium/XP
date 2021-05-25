package pl.edu.agh.xp.advertisements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class AdvertisementService {

    private final CSVReader csvReader;
    private final CSVWriter csvWriter;
    private final AdvertisementsPrinter advertisementsPrinter;
    private final AdvertisementCreator advertisementCreator;
    private ConsoleReader consoleReader;
    private final String advertisementsCsvPath;

    public void addAdvertisement() {
        var ad = advertisementCreator.createFromConsole();
        csvWriter.write(advertisementsCsvPath, ad);
    }

    public void printAdvertisement() {
        var advertisements = csvReader.read(advertisementsCsvPath);
        advertisementsPrinter.print(advertisements);
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


