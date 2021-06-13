package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.auth.AuthenticationService;
import pl.edu.agh.xp.advertisements.auth.AuthenticationServiceFactory;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.Login;
import pl.edu.agh.xp.advertisements.menu.MenuFactory;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.configuration.ConfigurationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AdvertisementsApplication {

    public static void main(String... args) {
        var app = new AdvertisementsApplication();
        app.start(System.in,  System.out);
    }

    public void start(InputStream in, PrintStream out) {
        this.setupContext(in, out);
        this.login(out);
        this.showMenu(out);
    }

    private void setupContext(InputStream in, PrintStream out) {
        var configurationService = new ConfigurationService();
        ServiceProvider.addService(ConfigurationService.class, configurationService);
        configurationService.readConfiguration();

        ServiceProvider.addService(InputStream.class, in);
        ServiceProvider.addService(PrintStream.class, out);

        var reader = new ConsoleReader(in, out);
        ServiceProvider.addService(ConsoleReader.class, reader);

        var advertisementService = new AdvertisementService(reader, out, FileName.create(AdvertisementConfiguration.INSTANCE.pathToAdvertisements));
        ServiceProvider.addService(AdvertisementService.class, advertisementService);

        var authenticationService = AuthenticationServiceFactory.create();
        ServiceProvider.addService(AuthenticationService.class, authenticationService);
    }

    private void login(PrintStream out) {
        var logged = false;
        // invoke login
        var authenticationService = (AuthenticationService) ServiceProvider.getService(AuthenticationService.class);
        var reader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
        while (!logged) {
            try {
                Login.login(authenticationService, reader);
                logged = true;
            } catch (RuntimeException e) {
                out.println("Error! " + e.getMessage());
            }

        }
    }

    private void showMenu(PrintStream out) {
        var in = (InputStream) ServiceProvider.getService(InputStream.class);
        out.println("Hello in Advertisement Management System!");
        var menu = MenuFactory.create();
        var scanner = new Scanner(in);
        String input;
        while (true) {
            menu.printMenu();
            input = scanner.nextLine();
            if (input != null) {
                try {
                    menu.handleInput(input);
                } catch (RuntimeException e) {
                    out.println("Error! " + e.getMessage());
                }
            }
        }
    }
}
