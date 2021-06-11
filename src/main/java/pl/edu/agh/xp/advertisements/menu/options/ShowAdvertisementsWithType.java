package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;
import java.util.HashMap;

public class ShowAdvertisementsWithType extends MenuOption {

    private final AdvertisementService advertisementService;
    private final ConsoleReader consoleReader;

    public ShowAdvertisementsWithType(PrintStream out) {
        super("Show advertisements with type", out);
        this.advertisementService = (AdvertisementService) ServiceProvider.getService(AdvertisementService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public void doAction() {
        var types = new HashMap<Integer, String>();
        for (int i = 0; i < AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.size(); i++) {
            types.put(i, AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.get(i));
        }
        var id = consoleReader.readInteger("Select advertisement type by number and press Enter. Available types are: " + types.entrySet());
        advertisementService.printAdvertisementWithType(types.get(id));
    }
}
