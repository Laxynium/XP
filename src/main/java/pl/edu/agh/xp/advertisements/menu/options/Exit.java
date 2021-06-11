package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class Exit extends MenuOption {

    public Exit(PrintStream out) {
        super("Exit", out);
    }

    @Override
    public void doAction() {
        System.exit(0);
    }
}
