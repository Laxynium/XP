package pl.agh.xp.Advertisements.StringCalculator;

import org.springframework.stereotype.Service;

@Service
public class StringCalculator {

    public int add(String numbers) {
        var splitNumbers = numbers.split(",");
        int sum = 0;
        for (String s : splitNumbers) {
            if (!s.isEmpty()) {
                sum += Integer.parseInt(s.trim());
            }
        }
        return sum;
    }
}
