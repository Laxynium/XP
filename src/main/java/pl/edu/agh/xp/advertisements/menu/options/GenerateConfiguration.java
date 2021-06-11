package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.configuration.ConfigurationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;

public class GenerateConfiguration extends MenuOption {

    private final ConfigurationService configurationService;
    private final ConsoleReader consoleReader;

    public GenerateConfiguration(PrintStream out) {
        super("Generate system configuration", out);
        this.configurationService = (ConfigurationService) ServiceProvider.getService(ConfigurationService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public void doAction() {
        configurationService.save(consoleReader.readString("In which directory do you want to save configuration?\nPlease enter relative path: ") + "/configuration.json");
    }
}


