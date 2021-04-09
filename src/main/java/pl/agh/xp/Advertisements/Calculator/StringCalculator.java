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

        List<String> splitNumbers = new ArrayList<>();
        Arrays.stream(numbers.split(delimiters)).filter(number -> !number.isEmpty()).forEach(splitNumbers::add);

        int sum= 0;
        for(String number : splitNumbers) {
            sum+=Integer.parseInt(number);
        }
        return sum;
    }
}
