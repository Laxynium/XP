package pl.edu.agh.xp.advertisements.auth;

import pl.edu.agh.xp.advertisements.model.LoggedInUser;

public interface AuthenticationService {

    LoggedInUser login(String username, String password);

}
