package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

@Value
public class AdvertisementType {

    String value;

    @JsonCreator
    public static AdvertisementType create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Given advertisement type cannot be empty.");
        }

        validate(value);

        return new AdvertisementType(value);
    }

    @Override
    public String toString() {
        return value;
    }

    private static void validate(String value) {
        if (AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.stream().noneMatch(value::equalsIgnoreCase)) {
            throw new RuntimeException("Given advertisement type is incompatible with defined configuration");
        }
    }
}
