package pl.agh.xp.Advertisements.Calculator;

public class StringBitCalculator {
    int noOfBits1(String numbers){
        int sum = 0;
        for(String number : numbers.split("[;\s+]")) {
            if (number.length() > 8) {
                throw new RuntimeException(number);
            }
            if (!number.matches("[0-9a-fA-F]+")){
                throw new RuntimeException(number);
            }

            for (int i = 0; i < number.length(); ++i) {
                char c = number.charAt(i);
                if (c == '1') {
                    ++sum;
                }
            }
        }
        return sum;
    }
}
