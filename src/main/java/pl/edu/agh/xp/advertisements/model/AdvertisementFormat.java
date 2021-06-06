package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class AdvertisementFormat {
    String value;

    @JsonCreator()
    public static AdvertisementFormat create(String value){
        if(value == null || value.trim().isEmpty()){
            throw new RuntimeException("Given advertisement format cannot be empty.");
        }
        return new AdvertisementFormat(value);
    }

    @Override
    public String toString() {
        return value;
    }
}