package pl.edu.agh.xp.advertisements.menu;

import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.options.*;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.IntStream;

public class AdOwnerMenu extends Menu {

    public AdOwnerMenu() {
        var out = (PrintStream) ServiceProvider.getService(PrintStream.class);
        var menuOptions = Arrays.asList(
                new ShowAdvertisements(out),
                new ShowAdvertisementsWithType(out),
                new AddAdvertisement(out),
                new DeleteAdvertisement(out),
                new Exit(out)
        );

        this.addMenuOption("", new BeginMenu(out));
        IntStream.range(0, menuOptions.size()).forEach(i -> this.addMenuOption(String.valueOf(i + 1), menuOptions.get(i)));
        this.addMenuOption(".*", new Default(out));
    }
}
