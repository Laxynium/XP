package pl.edu.agh.xp.advertisements.auth;

import pl.edu.agh.xp.advertisements.model.LoggedInUser;
import pl.edu.agh.xp.advertisements.model.UserType;

public interface AuthenticationService {

    LoggedInUser login(String username, String password);

    void addUser(String username, String password, UserType userType);

    void deleteUser(String username);

}
