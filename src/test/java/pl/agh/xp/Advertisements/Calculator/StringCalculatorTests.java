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
            "13; 13",
            "12,20,14; 46"
        },
        delimiter = ';'
    )
    void addUpToThreeValidNumbers(String numbers, int expectedOutput)
    {
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }
}
