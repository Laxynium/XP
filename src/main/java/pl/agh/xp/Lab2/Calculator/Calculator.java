package pl.agh.xp.Lab2.Calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
    public String add(String number) {
        List<Double> numbers = new LinkedList<>();
        for (String num: number.split(",") ){
            numbers.add(Double.parseDouble(num));
        }
        Double sum = numbers.stream().reduce(0.0, Double::sum);
        return String.valueOf(sum);
    }
}
