package pl.edu.agh.xp.advertisements;

import java.util.Scanner;

public class AdvertisementsApplication {

    public static void main(String... args) {

        var adsFacade = new AdvertisementConfiguration().create(System.in, System.out, "data/advertisements.csv");

        var scanner = new Scanner(System.in);
        System.out.println("Hello in Advertisement app!");

        String input;
        while (true) {
            System.out.println("Select action: (write number and press enter)");
            System.out.println("1. Show advertisements");
            System.out.println("2. Add advertisement");
            System.out.println("3. Delete advertisement");
            System.out.println("4. Exit");
            input = scanner.nextLine();
            if (input != null) {
                switch (input) {
                    case "1" -> adsFacade.printAdvertisement();
                    case "2" -> adsFacade.addAdvertisement();
                    case "3" -> adsFacade.deleteAdvertisement();
                    case "4" -> System.exit(0);
                    default -> System.out.println("Wrong number!");
                }
            }
        }
    }
}
