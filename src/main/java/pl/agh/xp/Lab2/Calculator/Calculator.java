package pl.agh.xp.Lab2.Calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
    public int add(String number) {
        if (number.trim().equals("")){
            return 0;
        }
        List<Integer> numbers = new LinkedList<>();
        for (String num: number.trim().split(",") ){
            numbers.add(Integer.parseInt(num.trim()));
        }
        return numbers.stream().reduce(0, Integer::sum);
    }
}
