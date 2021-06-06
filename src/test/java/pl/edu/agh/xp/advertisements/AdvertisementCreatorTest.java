package pl.edu.agh.xp.advertisements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.model.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class AdvertisementCreatorTest {

    @Mock
    ConsoleReader consoleReader;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("correctAdvertisementInput")
    void createAdvertisement_shouldReturnCorrectAdvertisementObject_whenCorrectInput(Object[] params, Advertisement expected) {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doReturn(params[0]).when(consoleReader).readInteger(any());
        doReturn(params[1].toString(), Arrays.copyOfRange(params, 2, params.length)).when(consoleReader).readString(any());

        // when
        var result = sut.createFromConsole();

        // then
        assertEquals(expected, result);
    }

    @Test
    void createAdvertisement_shouldReturnNull_whenIncorrectInput() {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doThrow(new RuntimeException("Incorrect input")).when(consoleReader).readInteger(any());

        // when
        var result = sut.createFromConsole();

        // then
        assertNull(result);
    }

    private static Stream<Arguments> correctAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "1.0 USD", "price_type1", "url1", "title1", "details1"},
                        new Advertisement(1, AdvertisementType.create("type1"), AdvertisementFormat.create("format1"), "advertiser1", Price.create("1.0 USD"), PricingMethod.create("price_type1"), "url1", "title1", "details1"
                        )),
                Arguments.arguments(
                        new Object[]{2, "type2", "format2", "advertiser2", "2.0 USD", "price_type2", "url2", "title2", "details2"},
                        new Advertisement(2, AdvertisementType.create("type2"), AdvertisementFormat.create("format2"), "advertiser2", Price.create("2.0 USD"), PricingMethod.create("price_type2"), "url2", "title2", "details2"
                        ))
        );
    }

}
