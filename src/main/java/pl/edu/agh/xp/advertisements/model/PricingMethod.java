package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

@Value
public class PricingMethod {

    String value;

    @JsonCreator
    public static PricingMethod create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Given pricing method cannot be empty.");
        }

        validate(value);

        return new PricingMethod(value);
    }

    @Override
    public String toString() {
        return value;
    }

    private static void validate(String value) {
        if (AdvertisementConfiguration.INSTANCE.availablePricingMethods.stream().noneMatch(value::equalsIgnoreCase)) {
            throw new RuntimeException("Given pricing method is incompatible with defined configuration");
        }
    }
}
