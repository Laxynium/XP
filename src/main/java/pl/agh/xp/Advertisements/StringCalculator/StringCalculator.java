package pl.agh.xp.Advertisements.StringCalculator;

import org.springframework.stereotype.Service;

@Service
public class StringCalculator {

    public int add(String numbers) {
        var splitNumbers = numbers.split(",");
        if(splitNumbers[0].isBlank()){
            return 0;
        }
        if(splitNumbers.length == 1){
            return Integer.parseInt(splitNumbers[0]);
        }
        return Integer.parseInt(splitNumbers[0]) + Integer.parseInt(splitNumbers[1]);
    }
}
