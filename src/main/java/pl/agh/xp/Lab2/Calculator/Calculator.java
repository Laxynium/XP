package pl.agh.xp.Lab2.Calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
    public int add(String number) {
        List<Integer> numbers = new LinkedList<>();
        for (String num: number.split(",") ){
            numbers.add(Integer.parseInt(num));
        }
        int sum = numbers.stream().reduce(0, Integer::sum);
        return sum;
    }
}
