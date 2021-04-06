package pl.agh.xp.Advertisements.StringCalculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class StringCalculator {

    public int add(final String numbers) {
        var delimiters = ",|\n";
        var processedNumbers = numbers;

        if (numbers.startsWith("//")) {
            delimiters = delimiters + "|" + numbers.substring(2, numbers.indexOf('\n'));
            processedNumbers = numbers.substring(numbers.indexOf('\n'));
        }

        var splitNumbers = processedNumbers.split(delimiters);
        int sum = 0;
        var toThrow = new ArrayList<>();
        for (String s : splitNumbers) {
            if (!s.isEmpty()) {
                int value = Integer.parseInt(s.trim());
                sum += value;
                if (value < 0) {
                    toThrow.add(value);
                }

            }
        }
        if (!toThrow.isEmpty()) {
            throw new RuntimeException(toThrow.stream()
                    .map(x -> Integer.toString((int) x))
                    .collect(Collectors.joining(", "))
            );
        }

        return sum;
    }
}
