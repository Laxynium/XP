package pl.edu.agh.xp.advertisements.menu.options;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.context.ServiceProvider;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AddAdvertisementTest {

    @Mock
    AdvertisementService advertisementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ServiceProvider.addService(AdvertisementService.class, advertisementService);
    }

    @Test
    public void inputMatchesKey_actionIsExecuted() {
        // given
        var key = Integer.valueOf(1);
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var sut = new AddAdvertisement(printStream);

        //when
        sut.handle();

        //then
        verify(advertisementService, times(1)).addAdvertisement();
    }

}