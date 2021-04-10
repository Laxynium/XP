package pl.agh.xp.Advertisements.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class StringBitCalculatorTests {

    StringBitCalculator stringBitCalculator = new StringBitCalculator();

    @Test
    void countBitsNoNegativeNoDelimiters1(){
        String number = "";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 0;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters2(){
        String number = "01010101";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 4;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters3(){
        String number = "11111111";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 8;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimiters4(){
        String number = "00000000";
        var result =stringBitCalculator.noOfBits1(number);
        var expected = 0;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeNoDelimitersExpectThrow() {
        String number = "0000110011100";
        Executable result = () -> stringBitCalculator.noOfBits1(number);
        Assertions.assertThrows(Exception.class, result);
    }

    @Test
    void countBitsNoNegativeDelimiters1(){
        String number = "00000000;00000000";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 0;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeDelimiters2(){
        String number = "01010101;010101";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 7;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeDelimitersExpectThrow() {
        String number = "00;000011011111111";
        Executable result = () -> stringBitCalculator.noOfBits1(number);
        Assertions.assertThrows(Exception.class, result);
    }

    @Test
    void countBitsNoNegativeDelimitersWhitespace2(){
        String number = "01010101 010101";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 7;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeDelimitersTab2(){
        String number = "01010101   010101";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 7;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void countBitsNoNegativeIllegalDelimiter(){
        String number = "010101[]";
        Executable result = () -> stringBitCalculator.noOfBits1(number);
        Assertions.assertThrows(Exception.class, result);
    }
    @Test
    void countBitsNoNegativeHexValue(){
        String number = "$ff";
        var result = stringBitCalculator.noOfBits1(number);
        var expected = 7;
        Assertions.assertEquals(expected,result);
    }
}
