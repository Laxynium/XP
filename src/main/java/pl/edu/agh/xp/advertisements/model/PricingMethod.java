package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class PricingMethod {

    String value;

    @JsonCreator
    public static PricingMethod create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Given pricing method cannot be empty.");
        }
        return new PricingMethod(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
