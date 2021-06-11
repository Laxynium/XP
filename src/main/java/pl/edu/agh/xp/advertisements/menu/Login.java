package pl.edu.agh.xp.advertisements.menu;

import pl.edu.agh.xp.advertisements.auth.UsersFileAuthenticationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

public class Login {

    public static void login(UsersFileAuthenticationService usersFileAuthenticationService, ConsoleReader reader) {
        var username = reader.readString("Insert login:");
        var password = reader.readString("Insert password:");

        usersFileAuthenticationService.login(username, password);
    }
}
