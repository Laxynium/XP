package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.menu.MenuOption;

import java.io.PrintStream;

public class Default extends MenuOption {

    public Default(String key, PrintStream printStream) {
        super(key, "\"Wrong number!\"", printStream);
    }

    @Override
    public HandlingResult doAction() {
        printStream.println(message);
        return HandlingResult.SUCCESS;
    }

    @Override
    public String formatMessage() {
        return message;
    }

    @Override
    public void printMessage() {
        // empty implementation
    }

}
