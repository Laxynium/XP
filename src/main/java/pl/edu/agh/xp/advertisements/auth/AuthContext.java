package pl.edu.agh.xp.advertisements.auth;

import pl.edu.agh.xp.advertisements.model.LoggedInUser;

public class AuthContext {

    private static LoggedInUser loggedInUser = null;

    public static LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(LoggedInUser loggedInUser) {
        AuthContext.loggedInUser = loggedInUser;
    }
}
