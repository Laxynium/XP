package pl.edu.agh.xp.advertisements.menu.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GenerateAdvertisementsCsvFileTest {
    @Mock
    AdvertisementService advertisementService;

    @Mock
    ConsoleReader consoleReader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ServiceProvider.addService(AdvertisementService.class, advertisementService);
        ServiceProvider.addService(ConsoleReader.class, consoleReader);
    }

    @Test
    void generateAdvertisementsCsvFileIsCalled_actionIsExecuted() {
        var path = ".";
        var pathToFile = path + "/urls.csv";
        when(consoleReader.readString(anyString())).thenReturn(path);

        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new GenerateAdvertisementsCsvFile(printStream);

        sut.handle();

        verify(advertisementService, times(1)).generateAdvertisementsCsvFile(pathToFile);
    }
}