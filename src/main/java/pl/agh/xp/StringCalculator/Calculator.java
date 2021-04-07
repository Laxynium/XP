package pl.agh.xp.StringCalculator;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Calculator {
    public String add(String s) {
        var customDelimiter = detectCustomDelimiter(s);
        var separator = "[,\\n]";
        if(customDelimiter != null){
            separator = customDelimiter;
            s = s.split("\n",2)[1];
        }

        if (s == null || s.isBlank())
            return "0";
        var splitted = s.split(separator);
        var numbers = Arrays.stream(splitted).map(Float::parseFloat);
        var sum = numbers.reduce(0f, Float::sum);
        return new DecimalFormat("0.#").format(sum);
    }

    private String detectCustomDelimiter(String input){
        if(input.startsWith("//")){
            var beforeFirstLine = input.split("\n",2)[0];
            var separator = beforeFirstLine.replace("//","");
            return separator;
        }else{
            return null;
        }
    }
}
