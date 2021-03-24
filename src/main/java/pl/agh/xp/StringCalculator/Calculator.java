package pl.agh.xp.StringCalculator;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Calculator {
    public String add(String s) {
       if(s == null || s.isBlank())
           return "0";
       var splitted = s.split(",");
       var numbers = Arrays.stream(splitted).map(Float::parseFloat);
       var sum = numbers.reduce(0f, Float::sum);
       return new DecimalFormat("0.#").format(sum);
    }
}
