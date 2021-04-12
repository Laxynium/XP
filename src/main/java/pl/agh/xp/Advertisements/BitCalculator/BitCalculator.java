package pl.agh.xp.Advertisements.BitCalculator;

import java.util.Arrays;

public class BitCalculator {

    public int noOfBits1(String numbers) {
        return Arrays.stream(numbers.split("[;\\s]"))
                .map(this::noOfBits1InOneNumber)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private int noOfBits1InOneNumber(String number) {
        if (number.isEmpty()) {
            return 0;
        }
        var base = 10;
        if (number.startsWith("$")) {
            number = number.substring(1);
            base = 16;
        }
        var parsedNumber = Integer.parseInt(number, base);


        if (parsedNumber < 0 || parsedNumber > 255) {
            throw new NumberFormatException();
        }

        var count = 0;
        while (parsedNumber > 0) {
            count += parsedNumber % 2;
            parsedNumber = parsedNumber / 2;
        }

        return count;
    }

}
