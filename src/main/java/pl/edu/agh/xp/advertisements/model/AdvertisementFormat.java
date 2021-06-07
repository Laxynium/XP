package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

@Value
public class AdvertisementFormat {

    String value;

    @JsonCreator
    public static AdvertisementFormat create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Given advertisement format cannot be empty.");
        }

        validate(value);

        return new AdvertisementFormat(value);
    }

    @Override
    public String toString() {
        return value;
    }

    private static void validate(String value) {
        if (AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats.stream().noneMatch(value::equalsIgnoreCase)) {
            throw new RuntimeException("Given advertisement format is incompatible with defined configuration");
        }
    }
}