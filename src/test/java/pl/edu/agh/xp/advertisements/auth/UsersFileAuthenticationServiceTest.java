package pl.edu.agh.xp.advertisements.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.xp.advertisements.model.UserType;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UsersFileAuthenticationServiceTest {

    @TempDir
    File tempDir;

    @BeforeEach
    void beforeEach() {
        // reset auth context so that it didn't affect other tests
        AuthContext.setLoggedInUser(null);
    }

    @ParameterizedTest
    @MethodSource("correctCredentials")
    void should_returnLoggedInUser_whenCorrectCredentialsGiven(String username, String password, UserType userType) throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/users.csv");
        var usersFile = new File(tempDir, "users.csv");
        Files.copy(originalPath, usersFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(usersFile).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(usersFile.toPath()));

        var authenticationService = new UsersFileAuthenticationService(FileName.create(usersFile.getAbsolutePath()));
        assertNull(AuthContext.getLoggedInUser());

        // when
        var actual = authenticationService.login(username, password);

        // then
        assertNotNull(AuthContext.getLoggedInUser());
        assertEquals(AuthContext.getLoggedInUser(), actual);
        assertEquals(username, actual.getUsername());
        assertEquals(userType, actual.getUserType());
    }

    @ParameterizedTest
    @MethodSource("incorrectCredentials")
    void should_throwException_whenIncorrectCredentialsGiven(String username, String password, String exceptionMessage) throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/users.csv");
        var usersFile = new File(tempDir, "users.csv");
        Files.copy(originalPath, usersFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(usersFile).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(usersFile.toPath()));

        var authenticationService = new UsersFileAuthenticationService(FileName.create(usersFile.getAbsolutePath()));

        // when
        var exception = assertThrows(RuntimeException.class, () -> authenticationService.login(username, password));

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void should_addNewUser() throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/users.csv");
        var usersFile = new File(tempDir, "users.csv");
        Files.copy(originalPath, usersFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(usersFile).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(usersFile.toPath()));

        var authenticationService = new UsersFileAuthenticationService(FileName.create(usersFile.getAbsolutePath()));

        // when
        authenticationService.addUser("new_user", "new_password", UserType.AD_OWNER);

        // then
        var allLines = Files.readAllLines(usersFile.toPath());
        assertEquals(Files.readAllLines(originalPath).size() + 1, allLines.size());
        assertEquals("\"username\",\"password\",\"userType\"", allLines.get(0));
        assertEquals("\"new_user\",\"new_password\",\"AD_OWNER\"", allLines.get(allLines.size() - 1));
    }

    @Test
    void should_deleteUser() throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/users.csv");
        var usersFile = new File(tempDir, "users.csv");
        Files.copy(originalPath, usersFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(usersFile).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(usersFile.toPath()));

        var authenticationService = new UsersFileAuthenticationService(FileName.create(usersFile.getAbsolutePath()));

        // when
        authenticationService.deleteUser("adowner");

        // then
        var allLines = Files.readAllLines(usersFile.toPath());

        assertEquals(Files.readAllLines(originalPath).size() - 1, allLines.size());
        assertEquals("\"username\",\"password\",\"userType\"", allLines.get(0));
        assertEquals("\"admin\",\"admin\",\"ADMIN\"", allLines.get(1));
        assertEquals("\"adpublisher\",\"adpublisher\",\"AD_PUBLISHER\"", allLines.get(2));
    }

    private static Stream<Arguments> correctCredentials() {
        return Stream.of(
                Arguments.arguments("admin", "admin", UserType.ADMIN),
                Arguments.arguments("adowner", "adowner", UserType.AD_OWNER),
                Arguments.arguments("adpublisher", "adpublisher", UserType.AD_PUBLISHER)
        );
    }

    private static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                Arguments.arguments(null, "password", "Username cannot be empty"),
                Arguments.arguments(" ", "password", "Username cannot be empty"),
                Arguments.arguments("username", null, "Password cannot be empty"),
                Arguments.arguments("username", " ", "Password cannot be empty"),
                Arguments.arguments("username", "password", "User with given username has not been found"),
                Arguments.arguments("admin", "wrong_password", "Given password is incorrect"),
                Arguments.arguments("adowner", "123n1ke", "Given password is incorrect"),
                Arguments.arguments("adpublisher", "kas asdfoad asofdk", "Given password is incorrect")
        );
    }

}