package pl.edu.agh.xp.advertisements.menu.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ShowAdvertisementsTest {
    @Mock
    AdvertisementService advertisementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ServiceProvider.addService(AdvertisementService.class, advertisementService);
    }

    @Test
    public void showAdvertisementsIsCalled_actionIsExecuted() {
        // given
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new ShowAdvertisements(printStream);

        //when
        sut.handle();

        //then
        verify(advertisementService, times(1)).printAdvertisement();
    }
}