package pl.agh.xp.Advertisements.BitCalculator;

public class BitCalculator {

    public int noOfBits1(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        var parsedNumber = Integer.parseInt(numbers);
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
