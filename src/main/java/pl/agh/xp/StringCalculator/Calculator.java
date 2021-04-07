package pl.agh.xp.StringCalculator;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Calculator {
    public String add(String input) {
        var separator = "[,\\n]";
        if(input.startsWith("//")){
            String[] split = input.split("\n", 2);
            var beforeFirstLine = split[0];
            separator = beforeFirstLine.replace("//", "");
            input = split[1];
        }

        if (input == null || input.isBlank())
            return "0";
        var split = input.split(separator);
        var numbers = Arrays.stream(split).map(Float::parseFloat);
        var sum = numbers.reduce(0f, Float::sum);
        return new DecimalFormat("0.#").format(sum);
    }
}
