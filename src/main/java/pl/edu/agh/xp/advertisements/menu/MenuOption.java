package pl.edu.agh.xp.advertisements.menu;

import lombok.AllArgsConstructor;

import java.io.PrintStream;

@AllArgsConstructor
public abstract class MenuOption {
    protected String key;
    protected String message;
    protected PrintStream printStream;

    public abstract HandlingResult doAction();

    public HandlingResult handleInput(String input) {
        if (input.matches(key)) {
            return this.doAction();
        }
        return HandlingResult.SKIP;
    }

    public String formatMessage() {
        return String.format("%s. %s", key, message);
    }

    public void printMessage() {
        printStream.println(formatMessage());
    }

    public enum HandlingResult {
        SUCCESS,
        SKIP
    }

}
