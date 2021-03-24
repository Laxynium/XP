package pl.agh.xp.StringCalculator;

import java.util.Arrays;

public class Calculator {
    public String add(String s) {
       if(s == null || s.isBlank())
           return "0";
       var splitted = s.split(",");
       var numbers = Arrays.stream(splitted).map(Integer::parseInt);
       return numbers.reduce(0, Integer::sum).toString();
    }
}
