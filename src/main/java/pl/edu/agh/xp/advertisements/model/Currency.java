package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

@Value
public class Currency {

    String currency;

    @JsonCreator
    public static Currency create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Currency cannot be empty.");
        }

        validate(value);

        return new Currency(value);
    }

    @Override
    public String toString() {
        return currency;
    }

    private static void validate(String value) {
        if (AdvertisementConfiguration.INSTANCE.availableCurrencies.stream().noneMatch(value::equalsIgnoreCase)) {
            throw new RuntimeException("Given currency is incompatible with defined configuration");
        }
    }
}
