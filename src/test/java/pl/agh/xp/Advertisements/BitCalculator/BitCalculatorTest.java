package pl.agh.xp.Advertisements.BitCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BitCalculatorTest {

    BitCalculator bitCalculator = new BitCalculator();

    @ParameterizedTest
    @CsvSource(
            value = {
                    "0,0",
                    "'', 0",
                    "3, 2",
                    "4, 1",
                    "255, 8",
                    "1, 1"
            },
            delimiter = ','
    )
    void numOfBits1_countsNUmOf1Bits_whenNumberIsValid(String number, int output) {
        // when
        var result = bitCalculator.noOfBits1(number);
        // then
        Assertions.assertEquals(output, result);
    }

    @Test
    void numOfBits1_throwsException_whenNumberInWrongInterval() {
        // given
        var number = "-1";
        // when
        Executable executable = () -> bitCalculator.noOfBits1(number);
        // then
        Assertions.assertThrows(Exception.class, executable);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "0;0;0, 0",
                    ";;1;0, 1",
                    "3;2;1, 4",
                    "128;1, 2",
                    "3 2;1, 4",
                    "128 \t\t1, 2"
            },
            delimiter = ','
    )
    void numOfBits1_countsNumOf1Bits_whenNumbersHasMultipleValidNumbers(String number, int output) {
        // when
        var result = bitCalculator.noOfBits1(number);
        // then
        Assertions.assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    ";;$1;0, 1",
                    "3;2;$10, 4",
                    "3 2;$8, 4",
            },
            delimiter = ','
    )
    void numOfBits1_countsNumOf1Bits_whenNumbersHasNumbersInBase16Format(String number, int output) {
        // when
        var result = bitCalculator.noOfBits1(number);
        // then
        Assertions.assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "0;0x0",
                    ";;1d0",
            },
            delimiter = ','
    )
    void numOfBits1_throwsException_whenSeparatorIsNotCoMaOrWhitespace(String number) {
        // when
        Executable executable = () -> bitCalculator.noOfBits1(number);
        // then
        Assertions.assertThrows(Exception.class, executable);
    }

}