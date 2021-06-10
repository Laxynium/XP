package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = Advertisement.AdvertisementBuilder.class)
public class Advertisement {

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    @JsonSerialize(using = ToStringSerializer.class)
    private AdvertisementType type;
    @JsonSerialize(using = ToStringSerializer.class)
    private AdvertisementFormat format;
    private String advertiserMail;
    @JsonSerialize(using = ToStringSerializer.class)
    private Price price;
    @JsonSerialize(using = ToStringSerializer.class)
    private PricingMethod priceType;
    private String url;
    private String title;
    private String details;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AdvertisementBuilder{
    }

}
