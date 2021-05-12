package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.io.InputStream;
import java.io.PrintStream;

public class AdvertisementConfiguration {
    public AdvertisementFacade create(InputStream inputStream, PrintStream printStream, String advertisementCsvPath) {
        return new AdvertisementFacade(
                new CSVReader(),
                new CSVWriter(), new AdvertisementsPrinter(printStream),
                new AdvertisementCreator(new ConsoleReader(inputStream)),
                advertisementCsvPath);
    }
}
