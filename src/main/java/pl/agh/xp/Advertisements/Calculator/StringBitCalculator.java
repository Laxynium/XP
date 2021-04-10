package pl.agh.xp.Advertisements.Calculator;

public class StringBitCalculator {
    int noOfBits1(String numbers){
        int sum = 0;
        if(numbers=="") {
            return 0;
        }
        for(String number : numbers.split(";|\\s+")) {
            if (number.charAt(0)=='$'){
                number = number.substring(1);
                if (!number.matches("[0-9a-fA-F]+")){
                    throw new RuntimeException(number);
                }
                number = Integer.toBinaryString(Integer.parseInt(number, 16));
            }

            if (!number.matches("[0-1]+")){
                throw new RuntimeException(number);
            }

            if (number.length() > 8) {
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
