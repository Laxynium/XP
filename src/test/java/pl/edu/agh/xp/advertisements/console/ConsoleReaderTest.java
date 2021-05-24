package pl.edu.agh.xp.advertisements.console;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleReaderTest {

    @ParameterizedTest
    @MethodSource("correctIntegerValues")
    void readInteger_shouldReturnCorrectIntegerValue_whenCorrectIntegerInput(String input, int expected) {
        // given
        InputStream in = getInputStream(input);
        System.setIn(in);

        var sut = new ConsoleReader(System.in);

        var message = "Please enter an integer:";

        // when
        var result = sut.readInteger(message);

        // then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("incorrectIntegerValues")
    void readInteger_shouldThrowException_whenIncorrectIntegerInput(String input) {
        // given
        InputStream in = getInputStream(input);
        System.setIn(in);

        var sut = new ConsoleReader(System.in);

        var message = "Please enter an integer:";

        // when
        var exception = assertThrows(RuntimeException.class, () -> sut.readInteger(message));

        // then
        assertEquals("Incorrect input", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("correctStringValues")
    void readString_shouldReturnCorrectStringValue_whenCorrectStringInput(String input, String expected) {
        // given
        InputStream in = getInputStream(input);
        System.setIn(in);

        var sut = new ConsoleReader(System.in);

        var message = "Please enter a String value:";

        // when
        var result = sut.readString(message);

        // then
        assertEquals(expected, result);
    }

    private static Stream<Arguments> correctIntegerValues() {
        return Stream.of(
                Arguments.arguments("1", 1),
                Arguments.arguments("-100", -100),
                Arguments.arguments("0", 0)
        );
    }

    private static Stream<Arguments> incorrectIntegerValues() {
        return Stream.of(
                Arguments.arguments("5.2"),
                Arguments.arguments("test string"),
                Arguments.arguments("1a"),
                Arguments.arguments(" "),
                Arguments.arguments("\n"),
                Arguments.arguments("\t"),
                Arguments.arguments("")
        );
    }

    private static Stream<Arguments> correctStringValues() {
        return Stream.of(
                Arguments.arguments("123", "123"),
                Arguments.arguments("5.1", "5.1"),
                Arguments.arguments("test string", "test string"),
                Arguments.arguments("test123 5.1 string", "test123 5.1 string")
        );
    }

    private InputStream getInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

}
