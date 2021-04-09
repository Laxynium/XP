package pl.agh.xp.Advertisements.Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        String delimiters = ",|\n";
        if(numbers.startsWith("//")) {
            int endOfLine = numbers.indexOf("\n");
            delimiters = delimiters + "|" + numbers.substring(2,endOfLine);
            numbers = numbers.substring(endOfLine);
        }

        List<Integer> splitNumbers = new ArrayList<>();
        List<Integer> negativeNumbers = new ArrayList<>();
        Arrays.stream(numbers.split(delimiters))
                .filter(number -> !number.isEmpty())
                .map(Integer::parseInt)
                .forEach(number -> {
                    if(number<0){
                        negativeNumbers.add(number);
                    }
                    splitNumbers.add(number);
                });

        if(!negativeNumbers.isEmpty()){
            throw new RuntimeException(negativeNumbers.toString());
        }

        Integer sum= splitNumbers.stream().reduce(0,Integer::sum);
        return sum;
    }
}
