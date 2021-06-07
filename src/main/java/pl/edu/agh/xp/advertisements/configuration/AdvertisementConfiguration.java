package pl.edu.agh.xp.advertisements.configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration.Default.*;

@JsonDeserialize
@JsonSerialize
public class AdvertisementConfiguration {

    public static AdvertisementConfiguration INSTANCE = new AdvertisementConfiguration();

    public AdvertisementConfiguration() {
        this.pathToAdvertisements = defaultPathToAdvertisements;
        this.availableAdvertisementTypes = defaultAvailableAdvertisementTypes;
        this.availableAdvertisementFormats = defaultAvailableAdvertisementFormats;
        this.availableCurrencies = defaultAvailableCurrencies;
        this.availablePricingMethods = defaultAvailablePricingMethods;
    }

    public String pathToAdvertisements;
    public List<String> availableAdvertisementTypes;
    public List<String> availableAdvertisementFormats;
    public List<String> availableCurrencies;
    public List<String> availablePricingMethods;

    public static class Default {
        public static final String defaultPathToAdvertisements = "data/advertisements.csv";
        public static final List<String> defaultAvailableAdvertisementTypes = new ArrayList<>(List.of("IMAGE", "GIF", "VIDEO"));
        public static final List<String> defaultAvailableAdvertisementFormats = new ArrayList<>(List.of("SMALL", "MEDIUM", "LARGE"));
        public static final List<String> defaultAvailableCurrencies = new ArrayList<>(List.of("USD", "PLN", "EUR"));
        public static final List<String> defaultAvailablePricingMethods = new ArrayList<>(List.of("PER_VIEW", "PER_CLICK"));
    }

}
