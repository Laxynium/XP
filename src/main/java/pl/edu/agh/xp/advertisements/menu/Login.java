package pl.edu.agh.xp.advertisements.menu;

import pl.edu.agh.xp.advertisements.auth.AuthenticationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

public class Login {

    public static void login(AuthenticationService usersFileAuthenticationService, ConsoleReader reader) {
        var username = reader.readString("Please enter username:");
        var password = reader.readString("Please enter password:");

        usersFileAuthenticationService.login(username, password);
    }
}
