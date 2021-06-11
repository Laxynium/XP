package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.auth.AuthenticationService;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.model.UserType;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.stream.IntStream;

public class AddUser extends MenuOption {

    private final AuthenticationService authenticationService;
    private final ConsoleReader consoleReader;

    public AddUser(PrintStream out) {
        super("Add new user", out);
        this.authenticationService = (AuthenticationService) ServiceProvider.getService(AuthenticationService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public void doAction() {
        var userTypes = new HashMap<Integer, UserType>();
        IntStream.range(0, UserType.values().length).forEach(i -> {
            userTypes.put(i, UserType.values()[i]);
        });

        var userType = consoleReader.readInteger("Select user type by number and press Enter. Available types are: " + userTypes.entrySet());
        var username = consoleReader.readString("Please enter username: ");
        var password = consoleReader.readString("Please enter password: ");

        authenticationService.addUser(username, password, userTypes.get(userType));
    }
}
