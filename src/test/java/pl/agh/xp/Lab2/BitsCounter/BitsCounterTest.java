package pl.agh.xp.Lab2.BitsCounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BitsCounterTest {

    @Test
    void testSumBitsUpTo1NumberWhenNotNegative(){
        BitsCounter bitsCounter = new BitsCounter();

        String decimalNumInput = "8";
        int result = bitsCounter.noOfBits(decimalNumInput);
        assertEquals(1, result);
    }
}
