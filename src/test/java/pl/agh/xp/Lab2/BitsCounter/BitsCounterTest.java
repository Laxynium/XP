package pl.agh.xp.Lab2.BitsCounter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class BitsCounterTest {

    private static Stream<Arguments> countOneDecimalProvider() {
        return Stream.of(
                Arguments.of("8", 1),
                Arguments.of("9", 2)
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
}
