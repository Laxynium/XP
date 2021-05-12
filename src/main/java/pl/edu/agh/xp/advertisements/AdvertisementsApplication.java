package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.csv.CSVReader;
import pl.edu.agh.xp.advertisements.printer.AdvertisementsPrinter;
import pl.edu.agh.xp.advertisements.writer.CSVWriter;

import java.util.Scanner;

public class AdvertisementsApplication {

    public static void main(String... args) {
        var advertisementsPrinter = new AdvertisementsPrinter(System.out);
        var console = new ConsoleReader();
        var advertisementCreator = new AdvertisementCreator(console);
        var writer = new CSVWriter();
        var fileName = "data/advertisements.csv";
        var advertisements = CSVReader.read(fileName);
        var scanner = new Scanner(System.in);

        System.out.println("Hello in Advertisement app!");

        String input;
        while (true) {
            System.out.println("Select action: (write number and press enter)");
            System.out.println("1. Show advertisements");
            System.out.println("2. Add advertisement");
            System.out.println("3. Exit");
            input = scanner.nextLine();
            if (input != null) {
                switch (input) {
                    case "1" -> advertisementsPrinter.print(advertisements);
                    case "2" -> {
                        var ad = advertisementCreator.createFromConsole();
                        advertisements.add(ad);
                        writer.write(fileName, ad);
                    }
                    case "3" -> System.exit(0);
                    default -> System.out.println("Wrong number!");
                }
            }
        }
    }
}
