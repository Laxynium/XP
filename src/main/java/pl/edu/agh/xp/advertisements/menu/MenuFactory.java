package pl.edu.agh.xp.advertisements.menu;

import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.options.*;
import pl.edu.agh.xp.advertisements.model.UserType;

import java.io.PrintStream;

public class MenuFactory {

    public static MainMenu createMenu(UserType userType) {
        var out = (PrintStream) ServiceProvider.getService(PrintStream.class);
        var i = 1;

        var menu = MainMenu.create()
                .addMenuOption(new BeginMenu(out))
                .addMenuOption(new ShowAdvertisements(i++, out))
                .addMenuOption(new ShowAdvertisementsWithType(i++, out));
        switch (userType) {
            case ADMIN -> menu.addMenuOption(new AddAdvertisement(i++, out))
                    .addMenuOption(new DeleteAdvertisement(i++, out))
                    .addMenuOption(new GenerateConfiguration(i++, out));
            case AD_OWNER -> menu
                    .addMenuOption(new AddAdvertisement(i++, out))
                    .addMenuOption(new DeleteAdvertisement(i++, out));
            case AD_PUBLISHER -> {
            }
        }
        menu.addMenuOption(new Exit(i++, out))
                .addMenuOption(new Default("*", out));
        return menu;
    }

}