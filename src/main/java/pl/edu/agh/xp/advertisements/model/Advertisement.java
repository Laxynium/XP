package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = Advertisement.AdvertisementBuilder.class)
public class Advertisement {

    private Integer id;
    private AdvertisementType type;
    private AdvertisementFormat format;
    private String advertiserMail;
    private Price price;
    private PricingMethod priceType;
    private String url;
    private String title;
    private String details;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AdvertisementBuilder{
    }

}
