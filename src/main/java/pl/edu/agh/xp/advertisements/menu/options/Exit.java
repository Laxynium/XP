package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class Exit extends MenuOption {

    public Exit(Integer i, PrintStream out) {
        super(i.toString(), "Exit", out);
    }

    @Override
    public HandlingResult doAction() {
        System.exit(0);
        return HandlingResult.SUCCESS;
    }
}
