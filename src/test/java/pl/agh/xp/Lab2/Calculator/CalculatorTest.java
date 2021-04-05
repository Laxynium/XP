package pl.agh.xp.Lab2.Calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static Stream<Arguments> sumTwoValuesProvider() {
        return Stream.of(
                Arguments.of("1,2", 3),
                Arguments.of("-1,3", 2),
                Arguments.of("1", 1),
                Arguments.of(" ", 0),
                Arguments.of("-1    ,3", 2)
        );
    }

    @ParameterizedTest(name="{index} => numbersToAdd={0}, sum={1}")
    @MethodSource("sumTwoValuesProvider")
    void testAddUpTwoNumberWhenStringValid(String numbersToAdd, int sum){
        Calculator calculator = new Calculator();

        int result = calculator.add(numbersToAdd);
        assertEquals(sum, result);
    }

    @Test
    void testAddWhenEmptyString(){
        Calculator calculator = new Calculator();

        String numbersToAdd = "";
        int result = calculator.add(numbersToAdd);
        assertEquals(0, result);
    }

    private static Stream<Arguments> sumManyValuesProvider() {
        return Stream.of(
                Arguments.of("1,2,3", 6)
        );
    }

    @ParameterizedTest(name="{index} => numbersToAdd={0}, sum={1}")
    @MethodSource("sumManyValuesProvider")
    void testAddUpAnyNumberWhenStringValid(String numbersToAdd, int sum){
        Calculator calculator = new Calculator();

        int result = calculator.add(numbersToAdd);
        assertEquals(sum, result);
    }

    private static Stream<Arguments> sumNewLineDelimiterProvider() {
        return Stream.of(
                Arguments.of("1\n2,3", 6),
                Arguments.of("1\n3\n3", 7)
        );
    }

    @ParameterizedTest(name="{index} => numbersToAdd={0}, sum={1}")
    @MethodSource("sumNewLineDelimiterProvider")
    void testAddNewLineDelimiterWhenStringValid(String numbersToAdd, int sum){
        Calculator calculator = new Calculator();

        int result = calculator.add(numbersToAdd);
        assertEquals(sum, result);
    }
}