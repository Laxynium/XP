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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void createAdvertisement_shouldThrowRuntimeException_whenConsoleReaderThrowsException() {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doThrow(new RuntimeException("Incorrect input")).when(consoleReader).readInteger(any());

        // when
        var exception = assertThrows(RuntimeException.class, sut::createFromConsole);

        // then
        assertEquals("Incorrect input", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("incorrectAdvertisementInput")
    void createAdvertisement_shouldThrowRuntimeException_whenIncorrectInputGiven(Object[] params, String exceptionMessage) {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doReturn(params[0]).when(consoleReader).readInteger(any());
        doReturn(params[1] != null ? params[1].toString() : null, Arrays.copyOfRange(params, 2, params.length))
                .when(consoleReader).readString(any());

        // when
        var exception = assertThrows(RuntimeException.class, sut::createFromConsole);

        // then
        assertEquals(exceptionMessage, exception.getMessage());
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

    private static Stream<Arguments> incorrectAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Object[]{1, null, "format1", "advertiser1", "1.0 USD", "price_type1", "url1", "title1", "details1"},
                        "Given advertisement type cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, " ", "format1", "advertiser1", "1.0 USD", "price_type1", "url1", "title1", "details1"},
                        "Given advertisement type cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", null, "advertiser1", "1.0 USD", "price_type1", "url1", "title1", "details1"},
                        "Given advertisement format cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", " ", "advertiser1", "1.0 USD", "price_type1", "url1", "title1", "details1"},
                        "Given advertisement format cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", null, "price_type1", "url1", "title1", "details1"},
                        "Given price cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", " ", "price_type1", "url1", "title1", "details1"},
                        "Given price cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "1 U SD", "price_type1", "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "1", "price_type1", "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "$ USD", "price_type1", "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "X.5D USD", "price_type1", "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "1.0 USD", null, "url1", "title1", "details1"},
                        "Given pricing method cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, "type1", "format1", "advertiser1", "1.0 USD", " ", "url1", "title1", "details1"},
                        "Given pricing method cannot be empty."
                )
        );
    }

}
