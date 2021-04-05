package pl.agh.xp.Lab2.BitsCounter;

public class BitsCounter {
    public int noOfBits(String number) {
        if (number.trim().equals("")){
            return 0;
        }

        int parsedNumber = Integer.parseInt(number);

        if (parsedNumber<0 || parsedNumber > 255){
            throw new IllegalArgumentException("Number out of range 0-255");
        }

        String binaryNumber = Integer.toBinaryString(parsedNumber);

        return (int) binaryNumber.chars()
                .filter(ch -> ch=='1')
                .count();

    }
}
