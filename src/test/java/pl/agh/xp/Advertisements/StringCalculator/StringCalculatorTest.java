package pl.agh.xp.Advertisements.StringCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class StringCalculatorTest {

    @Test
    void Add_AddsUpToTwoNumbers_WhenStringIsValid() {
        StringCalculator sut = new StringCalculator();
        String[] numbers = {"","1", "1,2"};
        int[] expected = {0,1,3};
        for (int i=0; i<numbers.length; i++) {
            int result = sut.add(numbers[i]);
            Assertions.assertEquals(expected[i], result);
        }
    }

    @Test
    void Add_AddsUpToAnyNumbers_WhenStringIsValid() {
        StringCalculator sut = new StringCalculator();
        String[] numbers = {"1,2,3","10,90,10,20"};
        int[] expected = {6,130};
        for (int i=0; i<numbers.length; i++) {
            int result = sut.add(numbers[i]);
            Assertions.assertEquals(expected[i], result);
        }
    }

    @Test
    void Add_AddsNumbersUsingNewLineDelimeter_WhenStringIsValid() {
        StringCalculator sut = new StringCalculator();
        String[] numbers = {"1\n2,3","10\n90,10\n20"};
        int[] expected = {6,130};
        for (int i=0; i<numbers.length; i++) {
            int result = sut.add(numbers[i]);
            Assertions.assertEquals(expected[i], result);
        }

    }


    @Test
    void Add_AddsNumbersUsingCustomDelimeter_WhenStringIsValid() {
        StringCalculator sut = new StringCalculator();
        String[] numbers = {"//;\n1;2","//;\n1;2;4"};
        int[] expected = {3,7};
        for (int i=0; i<numbers.length; i++) {
            int result;
            result = sut.add(numbers[i]);
            Assertions.assertEquals(expected[i], result);
        }
    }


    @Test
    void Add_ShouldThrowAnException_WhenNegativeNumbersAreUsed() {
        StringCalculator sut = new StringCalculator();
        String[] numbers = {"1,2,-1","//;\n;-2;-4"};
        String[] expected = {"-1","-2,-4"};

        Assertions.assertThrows(Exception.class, () -> sut.add(numbers[0]),"Negatives not allowed " + expected[0]);
        Assertions.assertThrows(Exception.class, () -> sut.add(numbers[1]),"Negatives not allowed " + expected[1]);
    }

}
