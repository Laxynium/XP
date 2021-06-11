package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class BeginMenu extends MenuOption {

    public BeginMenu(PrintStream out) {
        super("\nSelect action: (write number and press enter)", out);
    }

    @Override
    public void doAction() {
        // do nothing
    }

    @Override
    public String formatMessage(String key) {
        return message;
    }

}
