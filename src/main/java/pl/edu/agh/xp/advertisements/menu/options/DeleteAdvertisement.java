package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;

public class DeleteAdvertisement extends MenuOption {

    private final AdvertisementService advertisementService;
    private final ConsoleReader consoleReader;

    public DeleteAdvertisement(PrintStream out) {
        super("Delete advertisement", out);
        this.advertisementService = (AdvertisementService) ServiceProvider.getService(AdvertisementService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public void handle() {
        var id = consoleReader.readInteger("Please enter id of advertisement to delete:");
        advertisementService.deleteAdvertisement(id);
    }
}
