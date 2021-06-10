package pl.edu.agh.xp.advertisements.auth;

import pl.edu.agh.xp.advertisements.model.LoggedInUser;
import pl.edu.agh.xp.advertisements.model.User;
import pl.edu.agh.xp.advertisements.service.csv.CSVReader;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

public class UsersFileAuthenticationService implements AuthenticationService {

    private final CSVReader<User> csvReader;
    private final FileName pathToUsersFile;

    public UsersFileAuthenticationService(FileName pathToUsersFile) {
        this.csvReader = new CSVReader<>(User.class, true);
        this.pathToUsersFile = pathToUsersFile;
    }

    @Override
    public LoggedInUser login(String username, String password) {
        validateCredentials(username, password);

        var usersFromFile = csvReader.read(pathToUsersFile);
        var foundUser = usersFromFile.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User with given username has not been found"));

        if (!password.equals(foundUser.getPassword())) {
            throw new RuntimeException("Given password is incorrect");
        }

        var loggedInUser = LoggedInUser.builder()
                .username(foundUser.getUsername())
                .userType(foundUser.getUserType())
                .build();

        AuthContext.setLoggedInUser(loggedInUser);
        return loggedInUser;
    }

    private void validateCredentials(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }
    }

}
