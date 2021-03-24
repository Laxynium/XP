package pl.agh.xp.Advertisements.StringCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @ParameterizedTest
    @CsvSource({
            "\"\", 0",
            "\"1\", 1",
            "\"1,2\", 3"
    })
    void Add_addsUpToTwoNumbers_whenStringIsValid(String numbers, int output) {
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }


}