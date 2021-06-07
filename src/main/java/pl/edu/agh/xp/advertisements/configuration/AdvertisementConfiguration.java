package pl.edu.agh.xp.advertisements.configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize
@JsonSerialize
public class AdvertisementConfiguration {

    public static AdvertisementConfiguration INSTANCE = new AdvertisementConfiguration();

    public String pathToAdvertisements = "data/advertisements.csv";
    public List<String> availableAdvertisementTypes = new ArrayList<>(List.of("IMAGE", "GIF", "VIDEO"));
    public List<String> availableAdvertisementFormats = new ArrayList<>(List.of("SMALL", "MEDIUM", "LARGE"));
    public List<String> availableCurrencies = new ArrayList<>(List.of("USD", "PLN", "EUR"));
    public List<String> availablePricingMethods = new ArrayList<>(List.of("PER_VIEW", "PER_CLICK"));

}
