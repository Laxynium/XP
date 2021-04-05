package pl.agh.xp.Lab2.BitsCounter;

public class BitsCounter {
    public int noOfBits(String numbers) {

        String splitRegex = ";|\\s+";
        String numberRange = "[-]?[0-9][0-9]*";

        if (numbers.trim().equals("")){
            return 0;
        }

        int counter = 0;

        for (String number :
                numbers.split(splitRegex)) {
            if (!number.matches(numberRange)){
                throw new IllegalArgumentException("Invalid delimiters");
            }
            int parsedNumber = Integer.parseInt(number);

            if (parsedNumber<0 || parsedNumber > 255){
                throw new IllegalArgumentException("Number out of range 0-255");
            }
            String binaryNumber = Integer.toBinaryString(parsedNumber);

            counter += (int) binaryNumber.chars()
                    .filter(ch -> ch=='1')
                    .count();

        }

        return counter;
    }
}
