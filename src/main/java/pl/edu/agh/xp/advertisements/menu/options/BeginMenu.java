package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class BeginMenu extends MenuOption {

    public BeginMenu(PrintStream out) {
        super("", "\nSelect action: (write number and press enter)", out);
    }

    @Override
    public HandlingResult doAction() {
        return HandlingResult.SUCCESS;
    }

    @Override
    public String formatMessage() {
        return message;
    }

    @Override
    public void printMessage() {
        printStream.println(formatMessage());
    }

}
