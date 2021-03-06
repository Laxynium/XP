package pl.edu.agh.xp.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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


    @Test
    void Add_addsUpToAnyNumbers_whenStringIsSeparatedByComasAndNewLinesAndValid1() {
        // given
        var numbers = "1\n2,3";
        var output = 6;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_addsUpToAnyNumbers_whenStringIsSeparatedByComasAndNewLinesAndValid2() {
        // given
        var numbers = "2,3\n5\n10";
        var output = 20;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_addsUpToAnyNumbers_whenStringIsSeparatedByComasAndNewLinesAndValid3() {
        // given
        var numbers = "1,\n\n 3, 5\n 7";
        var output = 16;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_addsUpToAnyNumbers_whenStringHasCustomDelimiterAndIsSeparatedByComasAndNewLinesAndValid1() {
        // given
        var numbers = "//;\n1\n2;3";
        var output = 6;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_addsUpToAnyNumbers_whenStringHasCustomDelimiterAndIsSeparatedByComasAndNewLinesAndValid2() {
        // given
        var numbers = "2,3\n5\n10";
        var output = 20;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_addsUpToAnyNumbers_whenStringHasCustomDelimiterAndIsSeparatedByComasAndNewLinesAndValid3() {
        // given
        var numbers = "//,\n1,\n\n 3, 5\n 7";
        var output = 16;
        // when
        var result = stringCalculator.add(numbers);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void Add_shouldThrowAnException_whenNegativeNumbersAreUsed_oneNegative() {
        // given
        var numbers = "-1,3\n5\n10";
        var output = 20;
        // when
        Executable executable = () -> stringCalculator.add(numbers);
        // then
        Assertions.assertThrows(Exception.class, executable, "-1");
    }

    @Test
    void Add_shouldThrowAnException_whenNegativeNumbersAreUsed_moreNegatives() {
        // given
        var numbers = "-1,-23\n5\n10";
        var output = 20;
        // when
        Executable executable = () -> stringCalculator.add(numbers);
        // then
        Assertions.assertThrows(Exception.class, executable, "-1, -23");
    }

    @Test
    void Add_shouldThrowAnException_whenNegativeNumbersAreUsedAndCustomSeparator() {
        // given
        var numbers = "//,\n1,\n\n 3, -5\n 7";
        var output = 16;
        // when
        Executable executable = () -> stringCalculator.add(numbers);
        // then
        Assertions.assertThrows(Exception.class, executable, "-5");
    }

}