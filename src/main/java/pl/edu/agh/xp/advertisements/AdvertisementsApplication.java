package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.auth.AuthContext;
import pl.edu.agh.xp.advertisements.auth.UsersFileAuthenticationService;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.Login;
import pl.edu.agh.xp.advertisements.menu.MainMenu;
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
        app.setupContext();
        app.login();
        app.setupUserBasedContext();
        app.start();
    }

    private void setupContext() {
        var configurationService = new ConfigurationService();
        ServiceProvider.addService(ConfigurationService.class, configurationService);
        configurationService.readConfiguration();

        InputStream in = System.in;
        PrintStream out = System.out;

        ServiceProvider.addService(InputStream.class, in);
        ServiceProvider.addService(PrintStream.class, out);

        var reader = new ConsoleReader(in, out);
        ServiceProvider.addService(ConsoleReader.class, reader);

        var advertisementService = new AdvertisementService(reader, out, FileName.create(AdvertisementConfiguration.INSTANCE.pathToAdvertisements));
        ServiceProvider.addService(AdvertisementService.class, advertisementService);
    }

    private void setupUserBasedContext() {
        var user =         AuthContext.getLoggedInUser();
        var menu = MenuFactory.createMenu(user.getUserType());
        ServiceProvider.addService(MainMenu.class, menu);
    }

    private void login() {
        // wywołane logowanie
        var usersFileAuthenticationService = new UsersFileAuthenticationService( FileName.create(AdvertisementConfiguration.INSTANCE.pathToUsers));
        ServiceProvider.addService(UsersFileAuthenticationService.class, usersFileAuthenticationService);
        var reader = (ConsoleReader)ServiceProvider.getService(ConsoleReader.class);
        Login.login(usersFileAuthenticationService, reader);
    }

    private void start() {
        var menu = (MainMenu) ServiceProvider.getService(MainMenu.class);
        var in = (InputStream) ServiceProvider.getService(InputStream.class);
        var out = (PrintStream) ServiceProvider.getService(PrintStream.class);
        out.println("Hello in Advertisement Management System!");
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
