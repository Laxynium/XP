package pl.agh.xp.Advertisements.StringCalculator;

import org.springframework.stereotype.Service;

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
        for (String s : splitNumbers) {
            if (!s.isEmpty()) {
                sum += Integer.parseInt(s.trim());
            }
        }
        return sum;
    }
}
