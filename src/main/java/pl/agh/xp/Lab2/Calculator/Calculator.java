package pl.agh.xp.Lab2.Calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calculator {
    public int add(String number) {
        List<Character> delimiters = new ArrayList<>();
        delimiters.add(',');
        delimiters.add('\n');


        if (number.trim().equals("")) {
            return 0;
        }

        if (number.startsWith("//")){
            String[] splitOnFirstLine = number.split("\n", 2);
            String customDelimiter = splitOnFirstLine[0].replace("//", "");
            delimiters.add(customDelimiter.charAt(0));
            number = splitOnFirstLine[1];
        }

        StringBuilder regex = new StringBuilder("[");

        for (char delimiter :
                delimiters) {
            regex.append(delimiter);
        }
        regex.append("]");

        List<Integer> numbers = new LinkedList<>();
        for (String num: number.trim().split(regex.toString()) ){
            numbers.add(Integer.parseInt(num.trim()));
        }
        return numbers.stream().reduce(0, Integer::sum);
    }
}
