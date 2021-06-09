package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

import java.util.Scanner;

public class AdvertisementsApplication {

    public static void main(String... args) {

        var adsFacade = new AdvertisementFacade(System.in, System.out, AdvertisementConfiguration.INSTANCE.pathToAdvertisements);
        adsFacade.readConfiguration();

        var scanner = new Scanner(System.in);
        System.out.println("Hello in Advertisement Management System!");

        String input;
        while (true) {
            System.out.println("\nSelect action: (write number and press enter)");
            System.out.println("1. Show all advertisements");
            System.out.println("2. Show advertisements with type");
            System.out.println("3. Add advertisement");
            System.out.println("4. Delete advertisement");
            System.out.println("5. Generate system configuration");
            System.out.println("6. Exit");
            input = scanner.nextLine();
            if (input != null) {
                try {
                    switch (input) {
                        case "1" -> adsFacade.printAdvertisement();
                        case "2" -> adsFacade.printAdvertisementWithType();
                        case "3" -> adsFacade.addAdvertisement();
                        case "4" -> adsFacade.deleteAdvertisement();
                        case "5" -> adsFacade.generateConfiguration();
                        case "6" -> System.exit(0);
                        default -> System.out.println("Wrong number!");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Error! " + e.getMessage());
                }
            }
        }
    }
}
