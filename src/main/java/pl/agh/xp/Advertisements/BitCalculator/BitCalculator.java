package pl.agh.xp.Advertisements.BitCalculator;

public class BitCalculator {

    public int noOfBits1(String number) {
        if (number.isEmpty()) {
            return 0;
        }
        var parsedNumber = Integer.parseInt(number);
        var count = 0;
        while (parsedNumber > 0) {
            count += parsedNumber % 2;
            parsedNumber = parsedNumber / 2;
        }

        return count;
    }

}
