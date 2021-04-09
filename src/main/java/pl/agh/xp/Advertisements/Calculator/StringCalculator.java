package pl.agh.xp.Advertisements.Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        List<String> splitNumbers = new ArrayList<>();
        Arrays.stream(numbers.split("[,\n]")).filter(number -> !number.isEmpty()).forEach(splitNumbers::add);

        int sum= 0;
        for(String number : splitNumbers) {
            sum+=Integer.parseInt(number);
        }
        return sum;
    }
}
