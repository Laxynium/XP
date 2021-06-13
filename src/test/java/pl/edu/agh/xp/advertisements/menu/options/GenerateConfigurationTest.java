package pl.edu.agh.xp.advertisements.menu.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.configuration.ConfigurationService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GenerateConfigurationTest {
    @Mock
    ConfigurationService configurationService;
    @Mock
    ConsoleReader consoleReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ServiceProvider.addService(ConfigurationService.class, configurationService);
        ServiceProvider.addService(ConsoleReader.class, consoleReader);
    }

    @Test
    void generateConfigurationIsCalled_actionIsExecuted() {
        var path = ".";
        var pathToFile = path + "/configuration.json";
        when(consoleReader.readString(anyString())).thenReturn(path);


        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new GenerateConfiguration(printStream);

        sut.handle();

        verify(configurationService, times(1)).save(pathToFile);
    }

}