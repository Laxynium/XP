package pl.edu.agh.xp.advertisements.menu.options;


import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;

import java.io.PrintStream;

public class ShowAdvertisements extends MenuOption {

    private final AdvertisementService advertisementService;

    public ShowAdvertisements(Integer i, PrintStream out) {
        super(i.toString(), "Show all advertisements", out);
        this.advertisementService = (AdvertisementService) ServiceProvider.getService(AdvertisementService.class);
    }

    @Override
    public HandlingResult doAction() {
        advertisementService.printAdvertisement();
        return HandlingResult.SUCCESS;
    }
}
