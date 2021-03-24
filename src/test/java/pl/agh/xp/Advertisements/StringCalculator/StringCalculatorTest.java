package pl.agh.xp.Advertisements.StringCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringCalculatorTest {

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
    void Add_addsUpToTwoNumbers_whenStringIsValid(String numbers, int output) {
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "1,2,3; 6",
                    "2,3,5,10; 20",
                    "1, 3, 5, 7; 16"
            },
            delimiter = ';'
    )
    void Add_addsUpToAnyNumbers_whenStringIsValid(String numbers, int output) {
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }


}