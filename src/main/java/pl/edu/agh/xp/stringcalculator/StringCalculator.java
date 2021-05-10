package pl.edu.agh.xp.stringcalculator;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Service
public class StringCalculator {

    public int add(final String numbers) {
        var delimiters = ",|\n";
        var processedNumbers = numbers;

        if (numbers.startsWith("//")) {
            int firstNewLine = numbers.indexOf('\n');
            delimiters = delimiters + "|" + numbers.substring(2, firstNewLine);
            processedNumbers = numbers.substring(firstNewLine);
        }

        var splitNumbers = Arrays.stream(processedNumbers.split(delimiters))
                .filter(s1 -> !s1.isEmpty())
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        var negativeNumbers = splitNumbers.stream()
                .filter(n -> n < 0)
                .map(Objects::toString)
                .collect(Collectors.toList());

        if (negativeNumbers.size() != 0) {
            throw new RuntimeException(String.join(",", negativeNumbers));
        }
        return splitNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
