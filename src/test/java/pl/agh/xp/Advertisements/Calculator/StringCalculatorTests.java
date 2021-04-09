package pl.agh.xp.Advertisements.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
    void addUpToTwoNumbersWhenStringIsValid(String numbers, int expectedOutput) {
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
    void addUpToAnyNumbersWhenStringIsValid(String numbers, int expectedOutput) {
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void addUpToAnyNumbersUsingNewLineDelimiterWhenStringIsValid() {
        String numbers = "12\n200,14";
        int expectedOutput = 226;
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }
    @Test
    void addUpToAnyNumbersUsingOnlyNewLineDelimiterWhenStringIsValid() {
        String numbers = "12\n20\n143\n15";
        int expectedOutput = 190;
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void addUpToAnyNumbersUsingCustomDelimiterWhenStringIsValid() {
        String numbers = "//;\n1;2";
        int expectedOutput = 3;
        int result = stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    void addShouldThrowAnExceptionWhenNegativeNumbersAreUsed1() {
        String numbers = "1;2;-1;-2";
        int expectedOutput = 3;
        Executable result = () -> stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
        Assertions.assertThrows(Exception.class, result, "-1, -2");
    }

    @Test
    void addShouldThrowAnExceptionWhenNegativeNumbersAreUsed2() {
        String numbers = "1;2;-1;-2";
        int expectedOutput = 3;
        Executable result = () -> stringCalculator.add(numbers);
        Assertions.assertEquals(expectedOutput, result);
        Assertions.assertThrows(Exception.class, result, "-1, -2");
    }
}
