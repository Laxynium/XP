package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class AdvertisementType {

    String value;

    @JsonCreator
    public static AdvertisementType create(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Given advertisement type cannot be empty.");
        }
        return new AdvertisementType(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
