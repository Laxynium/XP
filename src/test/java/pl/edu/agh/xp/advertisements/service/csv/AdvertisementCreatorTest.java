package pl.edu.agh.xp.advertisements.service.csv;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.model.*;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementCreator;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.util.List;
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

    @AfterAll
    static void after() {
        // reset configuration to defaults so that it didn't affect other tests
        AdvertisementConfiguration.INSTANCE = new AdvertisementConfiguration();
    }

    @ParameterizedTest
    @MethodSource("correctAdvertisementInput")
    void createAdvertisement_shouldReturnCorrectAdvertisementObject_whenCorrectInput(Object[] params, Advertisement expected) {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doReturn(params[0], params[1], params[2], params[5]).when(consoleReader).readInteger(any());
        doReturn(params[3], params[4], params[6], params[7], params[8]).when(consoleReader).readString(any());

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
        doReturn(params[0], params[1], params[2], params[5]).when(consoleReader).readInteger(any());
        doReturn(params[3], params[4], params[6], params[7], params[8]).when(consoleReader).readString(any());

        // when
        var exception = assertThrows(RuntimeException.class, sut::createFromConsole);

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("incompatibleAdvertisementInput")
    void createAdvertisement_shouldThrowRuntimeException_whenInputIncompatibleWithDefinedConfiguration(Object[] params, String exceptionMessage) {
        // given
        var sut = new AdvertisementCreator(consoleReader);
        doReturn(params[0], params[1], params[2], params[5]).when(consoleReader).readInteger(any());
        doReturn(params[3], params[4], params[6], params[7], params[8]).when(consoleReader).readString(any());

        AdvertisementConfiguration.INSTANCE = new AdvertisementConfiguration();
        AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes = List.of("IMAGE", "VIDEO");
        AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats = List.of("MEDIUM", "LARGE");
        AdvertisementConfiguration.INSTANCE.availableCurrencies = List.of("USD", "PLN");
        AdvertisementConfiguration.INSTANCE.availablePricingMethods = List.of("PER_VIEW", "PER_CLICK");

        // when
        var exception = assertThrows(RuntimeException.class, sut::createFromConsole);

        // then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    private static Stream<Arguments> incompatibleAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Object[]{1, 0, 0, "advertiser1", "1.0 EUR", 0, "url1", "title1", "details1"},
                        "Given currency is incompatible with defined configuration"
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 0, "advertiser1", "21.37 UAH", 0, "url1", "title1", "details1"},
                        "Given currency is incompatible with defined configuration"
                ),
                Arguments.arguments(
                        new Object[]{1, 4, 0, "advertiser1", "1.0 EUR", 0, "url1", "title1", "details1"},
                        "Selected not correct advertisement type."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 0, "advertiser1", "1.0 USD", 4, "url1", "title1", "details1"},
                        "Selected not correct pricing method."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 4, "advertiser1", "1.0 EUR", 0, "url1", "title1", "details1"},
                        "Selected not correct advertisement format."
                )
        );
    }

    private static Stream<Arguments> correctAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Object[]{1, 0, 0, "advertiser1", "1.0 USD", 0, "url1", "title1", "details1"},
                        new Advertisement(1, AdvertisementType.create("IMAGE"), AdvertisementFormat.create("MEDIUM"), "advertiser1", Price.create("1.0 USD"), PricingMethod.create("PER_VIEW"), "url1", "title1", "details1"
                        )),
                Arguments.arguments(
                        new Object[]{2, 1, 1, "advertiser2", "2.0 PLN", 1, "url2", "title2", "details2"},
                        new Advertisement(2, AdvertisementType.create("VIDEO"), AdvertisementFormat.create("LARGE"), "advertiser2", Price.create("2.0 PLN"), PricingMethod.create("PER_CLICK"), "url2", "title2", "details2"
                        ))
        );
    }

    private static Stream<Arguments> incorrectAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Object[]{1, null, 1, "advertiser1", "1.0 USD", 0, "url1", "title1", "details1"},
                        "Selected not correct advertisement type."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, null, "advertiser1", "1.0 USD", 0, "url1", "title1", "details1"},
                        "Selected not correct advertisement format."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", null, 0, "url1", "title1", "details1"},
                        "Given price cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", " ", 0, "url1", "title1", "details1"},
                        "Given price cannot be empty."
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", "1 U SD", 0, "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", "1", 0, "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", "$ USD", 0, "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", "X.5D USD", 0, "url1", "title1", "details1"},
                        "Given price is in invalid format"
                ),
                Arguments.arguments(
                        new Object[]{1, 0, 1, "advertiser1", "1.0 USD", null, "url1", "title1", "details1"},
                        "Selected not correct pricing method."
                )
        );
    }

}
