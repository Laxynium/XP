package pl.edu.agh.xp.advertisements.menu;

import java.util.HashMap;
import java.util.Map;

public abstract class Menu {

    private final Map<String, MenuOption> menuOptions = new HashMap<>();

    public void addMenuOption(String key, MenuOption menuOption) {
        menuOptions.put(key, menuOption);
    }

    public void printMenu() {
        menuOptions.forEach((key, option) -> option.printMessage(key));
    }

    public void handleInput(String input) {
        for (String key : menuOptions.keySet()) {
            if (input.matches(key)) {
                menuOptions.get(key).handle();
                return;
            }
        }
    }

}
