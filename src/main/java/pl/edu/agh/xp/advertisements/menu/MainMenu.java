package pl.edu.agh.xp.advertisements.menu;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.xp.advertisements.menu.MenuOption.HandlingResult.SUCCESS;

public class MainMenu {

    private final List<MenuOption> menuOptions = new ArrayList<>();

    public static MainMenu create() {
        return new MainMenu();
    }

    public MainMenu addMenuOption(MenuOption menuOption) {
        menuOptions.add(menuOption);
        return this;
    }

    public void printMenu() {
        menuOptions.forEach(MenuOption::printMessage);
    }

    public void handleInput(String input) {
        for (MenuOption menuOption : menuOptions) {
            var handlingResult = menuOption.handleInput(input);
            if (SUCCESS.equals(handlingResult)) {
                return;
            }
        }
    }

}
