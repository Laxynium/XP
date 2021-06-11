package pl.edu.agh.xp.advertisements.menu.options;

import pl.edu.agh.xp.advertisements.auth.AuthenticationService;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.menu.MenuOption;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.PrintStream;

public class DeleteUser extends MenuOption {

    private final AuthenticationService authenticationService;
    private final ConsoleReader consoleReader;

    public DeleteUser(PrintStream out) {
        super("Delete user", out);
        this.authenticationService = (AuthenticationService) ServiceProvider.getService(AuthenticationService.class);
        this.consoleReader = (ConsoleReader) ServiceProvider.getService(ConsoleReader.class);
    }

    @Override
    public void handle() {
        var username = consoleReader.readString("Please enter username of user you want to delete: ");

        authenticationService.deleteUser(username);
    }
}
