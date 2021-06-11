package pl.edu.agh.xp.advertisements.menu;

import lombok.AllArgsConstructor;

import java.io.PrintStream;

@AllArgsConstructor
public abstract class MenuOption {
    protected String message;
    protected PrintStream printStream;

    public abstract void handle();

    public String formatMessage(String key) {
        return String.format("%s. %s", key, message);
    }

    public void printMessage(String key) {
        printStream.println(formatMessage(key));
    }

}
