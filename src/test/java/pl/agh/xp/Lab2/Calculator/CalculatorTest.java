package pl.agh.xp.Lab2.Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddUpTwoNumberWhenStringValid(){
        Calculator calculator = new Calculator();

        String numbersToAdd = "1,2";
        int result = calculator.add(numbersToAdd);
        assertEquals(3, result);
    }

    @Test
    void testAddWhenEmptyString(){
        Calculator calculator = new Calculator();

        String numbersToAdd = "";
        int result = calculator.add(numbersToAdd);
        assertEquals(0, result);
    }

}