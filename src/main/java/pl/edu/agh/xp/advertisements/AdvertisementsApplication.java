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
            System.out.println("1. Show advertisements");
            System.out.println("2. Add advertisement");
            System.out.println("3. Delete advertisement");
            System.out.println("4. Generate system configuration");
            System.out.println("5. Exit");
            input = scanner.nextLine();
            if (input != null) {
                switch (input) {
                    case "1" -> {
                        try {
                            adsFacade.printAdvertisement();
                        } catch (RuntimeException e) {
                            System.out.println("Error! " + e.getMessage());
                        }
                    }
                    case "2" -> {
                        try {
                            adsFacade.addAdvertisement();
                        } catch (RuntimeException e) {
                            System.out.println("Error! " + e.getMessage());
                        }
                    }
                    case "3" -> {
                        try {
                            adsFacade.deleteAdvertisement();
                        } catch (RuntimeException e) {
                            System.out.println("Error! " + e.getMessage());
                        }
                    }
                    case "4" -> {
                        try {
                            adsFacade.generateConfiguration();
                        } catch (RuntimeException e) {
                            System.out.println("Error! " + e.getMessage());
                        }
                    }
                    case "5" -> System.exit(0);
                    default -> System.out.println("Wrong number!");
                }
            }
        }
    }
}
