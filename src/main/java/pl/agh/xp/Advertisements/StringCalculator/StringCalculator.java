package pl.agh.xp.Advertisements.StringCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    int add(String numbers) {
        List<Character> delimiters =  ",\n".chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        if(numbers.equals("")) return 0;

        if(numbers.startsWith("//")){
            String[] splitOnFirstNewLine = numbers.split("\n",2);
            String customDelimiter = splitOnFirstNewLine[0].replace("//","");
            delimiters.add(customDelimiter.charAt(0));
            numbers = splitOnFirstNewLine[1];
        }
        int[] splitNumbers = Arrays.stream(numbers.split(delimiters.toString()))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] negativeNumbers = Arrays.stream(splitNumbers)
                .filter(i -> i < 0)
                .toArray();

        if (negativeNumbers.length != 0){
            String negatives = Arrays.toString(negativeNumbers);
            throw new RuntimeException("Negatives not allowed " + negatives.substring(1, negatives.length()-1));
        }

        return Arrays.stream(splitNumbers).sum();
    }
}
