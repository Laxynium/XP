package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;

public class DeleteAdvertisement extends MenuOption {

    private final AdvertisementService advertisementService;
    private final ConsoleReader consoleReader;

    public DeleteAdvertisement(Integer i, PrintStream out) {
        super(i.toString(), "Delete advertisement", out);
        this.advertisementService = (AdvertisementService) ServiceProvider.getService(AdvertisementService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public HandlingResult doAction() {
        var id = consoleReader.readInteger("Please enter id of advertisement to delete:");
        advertisementService.deleteAdvertisement(id);
        return HandlingResult.SUCCESS;
    }
}
