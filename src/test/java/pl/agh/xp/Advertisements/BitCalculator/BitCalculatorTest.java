package pl.agh.xp.Advertisements.BitCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BitCalculatorTest {

    BitCalculator bitCalculator = new BitCalculator();

    @ParameterizedTest
    @CsvSource(
            value = {
                    "0;0",
                    "; 0",
                    "3; 1",
                    "4; 1",
                    "255; 8",
                    "1; 1"
            },
            delimiter = ';'
    )
    void Add_addsUpToTwoNumbers_whenStringIsValid(String numbers, int output) {
        // when
        var result = bitCalculator.noOfBits1(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

}