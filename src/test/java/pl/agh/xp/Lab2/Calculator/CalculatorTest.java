package pl.agh.xp.Lab2.Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddUpTwoNumberWhenStringValid(){
        Calculator calculator = new Calculator();

        String numbersToAdd = "1,2";
        String result = calculator.add(numbersToAdd);
        assertEquals("3.0", result);
    }

}