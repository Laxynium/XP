package pl.edu.agh.xp.advertisements.menu;

import pl.edu.agh.xp.advertisements.auth.AuthContext;

public class MenuFactory {

    public static Menu create() {
        var loggedInUser = AuthContext.getLoggedInUser();
        if (loggedInUser == null) {
            throw new RuntimeException("User not logged in");
        }

        switch (loggedInUser.getUserType()) {
            case ADMIN -> {
                return new AdminMenu();
            }
            case AD_OWNER -> {
                return new AdOwnerMenu();
            }
            case AD_PUBLISHER -> {
                return new AdPublisherMenu();
            }
            default -> throw new RuntimeException("Unsupported user type");
        }
    }

}