package pl.agh.xp.Advertisements.Calculator;

public class StringBitCalculator {
    int noOfBits1(String number){
        int sum = 0;

        for(int i = 0; i<number.length(); ++i){
            char c = number.charAt(i);
            if(c == '1'){
                ++sum;
            }
        }
        return sum;
    }
}
