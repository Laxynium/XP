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

class DeleteAdvertisementTest {
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
    void deleteAdvertisementIsCalled_actionIsExecuted() {
        when(consoleReader.readInteger(anyString())).thenReturn(1);

        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new DeleteAdvertisement(printStream);

        sut.handle();

        verify(advertisementService, times(1)).deleteAdvertisement(1);

    }
}