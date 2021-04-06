package pl.agh.xp.Advertisements.StringCalculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<Boolean, List<Integer>> integers = Arrays.stream(processedNumbers.split(delimiters))
                .filter(s1 -> !s1.isEmpty())
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(groupingBy(x -> x > 0));

        boolean negative = false;
        if (!integers.getOrDefault(negative, List.of()).isEmpty()) {
            throw new RuntimeException(integers
                    .get(negative).stream()
                    .map(x -> Integer.toString((int) x))
                    .collect(Collectors.joining(", "))
            );
        }
        boolean positive = true;
        return integers.getOrDefault(positive, List.of())
                .stream()
                .mapToInt(x -> x)
                .sum();
    }
}
