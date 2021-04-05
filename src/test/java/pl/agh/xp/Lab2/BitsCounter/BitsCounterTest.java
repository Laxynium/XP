package pl.agh.xp.Lab2.BitsCounter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class BitsCounterTest {

    private static Stream<Arguments> countOneDecimalProvider() {
        return Stream.of(
                Arguments.of("8", 1),
                Arguments.of("9", 2),
                Arguments.of("0", 0)
        );
    }

    @ParameterizedTest(name="{index} => numberToCount={0}, resultCount={1}")
    @MethodSource("countOneDecimalProvider")
    void testSumBitsUpTo1NumberWhenNotNegative(String numberToCount, int resultCount){
        BitsCounter bitsCounter = new BitsCounter();


        int result = bitsCounter.noOfBits(numberToCount);
        assertEquals(resultCount, result);
    }

    @Test
    void testSumBitsWhenEmptyInput() {
        BitsCounter bitsCounter = new BitsCounter();

        int result = bitsCounter.noOfBits("");

        assertEquals(0, result);

    }

    @Test
    void testSumBitsWhenEmptyInputSeveralWhiteSpaces() {
        BitsCounter bitsCounter = new BitsCounter();

        int result = bitsCounter.noOfBits("          ");

        assertEquals(0, result);

    }

    @ParameterizedTest(name="{index} => numberToCount={0}, resultCount={1}")
    @ValueSource(strings = { "-1", "256"})
    void testSumBitsUpTo1NumberWhenOutOfBounds(String numberToCount){
        BitsCounter bitsCounter = new BitsCounter();


        Exception exception = assertThrows(IllegalArgumentException.class, ()-> bitsCounter.noOfBits(numberToCount));

        assertEquals("Number out of range 0-255", exception.getMessage());
    }

    private static Stream<Arguments> countSeveralDecimalProvider() {
        return Stream.of(
                Arguments.of("8;9", 3),
                Arguments.of("8;9;8", 4),
                Arguments.of("8;9 8", 4)
        );
    }

    @ParameterizedTest(name="{index} => numberToCount={0}, resultCount={1}")
    @MethodSource("countSeveralDecimalProvider")
    void testSumBitsUpToSeveralNumbers(String numberToCount, int resultCount){
        BitsCounter bitsCounter = new BitsCounter();


        int result = bitsCounter.noOfBits(numberToCount);
        assertEquals(resultCount, result);
    }

    private static Stream<Arguments> countWhiteSpaceDelimiterProvider() {
        return Stream.of(
                Arguments.of("8;9", 3),
                Arguments.of("8 9\t8", 4),
                Arguments.of("8  \t\n  9\t\t\t  \n\n\t8", 4)
        );
    }

    @ParameterizedTest(name="{index} => numberToCount={0}, resultCount={1}")
    @MethodSource("countWhiteSpaceDelimiterProvider")
    void testSumBitsWithWhiteSpacesDelimiters(String numberToCount, int resultCount){
        BitsCounter bitsCounter = new BitsCounter();


        int result = bitsCounter.noOfBits(numberToCount);
        assertEquals(resultCount, result);
    }
}
