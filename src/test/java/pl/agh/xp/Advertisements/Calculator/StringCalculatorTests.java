package pl.agh.xp.Advertisements.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCalculatorTests {

    StringCalculator stringCalculator = new StringCalculator();

    @ParameterizedTest
    @CsvSource(
            value = {
                    "''; 0",
                    "1; 1",
                    "1,2; 3"
            },
            delimiter = ';'
    )
    void addUpToTwoNumbersWhenStringIsValid(String numbers, int expectedOutput)
    {
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @CsvSource(
        value = {
            "''; 0",
            "13; 13",
            "12,20,14; 46",
            "12,200,14; 226",
            "12,20,143,15; 190"
        },
        delimiter = ';'
    )
    void addUpToAnyNumbersWhenStringIsValid(String numbers, int expectedOutput)
    {
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }
}
