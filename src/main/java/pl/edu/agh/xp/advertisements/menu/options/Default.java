package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class Default extends MenuOption {

    public Default(PrintStream printStream) {
        super("\"Wrong number!\"", printStream);
    }

    @Override
    public void doAction() {
        printStream.println(message);
    }

    @Override
    public void printMessage(String key) {
        // empty implementation
    }

}
