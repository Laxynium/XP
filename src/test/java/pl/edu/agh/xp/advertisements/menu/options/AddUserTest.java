package pl.edu.agh.xp.advertisements.menu.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.auth.AuthenticationService;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.model.UserType;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddUserTest {
    @Mock
    AuthenticationService authenticationService;

    @Mock
    ConsoleReader consoleReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ServiceProvider.addService(AuthenticationService.class, authenticationService);
        ServiceProvider.addService(ConsoleReader.class, consoleReader);
    }

    @Test
    void addUserIsCalled_actionIsExecuted() {
        var userName = "user";
        var password = "password";
        when(consoleReader.readInteger(anyString())).thenReturn(1);
        when(consoleReader.readString(contains("username"))).thenReturn(userName);
        when(consoleReader.readString(contains("password"))).thenReturn(password);
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new AddUser(printStream);

        sut.handle();

        verify(authenticationService, times(1)).addUser(userName, password, UserType.AD_OWNER);

    }
}