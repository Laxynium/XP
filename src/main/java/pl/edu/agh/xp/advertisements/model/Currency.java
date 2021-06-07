package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class Currency {

    String currency;

    @JsonCreator
    public static Currency create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Currency cannot be empty.");
        }
        return new Currency(value);
    }

    @Override
    public String toString() {
        return currency;
    }
}
