package pl.agh.xp.Advertisements.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBitCalculatorTests {

    StringBitCalculator stringBitCalculator = new StringBitCalculator();

    @Test
    void countBitsNoNegativeNoDelimiters1(){
        var result =stringBitCalculator.noOfBits1("");
        var expected = 1;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters2(){
        var result =stringBitCalculator.noOfBits1("01010101");
        var expected = 4;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters3(){
        var result =stringBitCalculator.noOfBits1("11111111");
        var expected = 8;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters4(){
        var result =stringBitCalculator.noOfBits1("00000000");
        var expected = 0;
        Assertions.assertEquals(expected,result);
    }
}
