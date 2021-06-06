package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Value
public class Price {
    BigDecimal amount;
    String currency;

    @JsonCreator()
    public static Price create(String price){
        if(price == null || price.trim().isEmpty()){
            throw new RuntimeException("Given price cannot be empty.");
        }
        var split = price.split(" ");
        if(split.length != 2){
            throw new RuntimeException("Give price is in invalid format");
        }

        var amount = parseAmount(split[0]);
        var currency = split[1];

        return new Price(amount, currency);
    }

    private static BigDecimal  parseAmount(String value){
        try{
            var format=  NumberFormat.getInstance(Locale.ENGLISH);
            var asDouble = format.parse(value).doubleValue();
            return BigDecimal.valueOf(asDouble).setScale(2, RoundingMode.DOWN);
        }catch (ParseException e){
            throw new RuntimeException("Given price is in invalid format");
        }
    }
    @Override
    public String toString() {
        var df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);

        return df.format(amount) + " " + currency;
    }
}
