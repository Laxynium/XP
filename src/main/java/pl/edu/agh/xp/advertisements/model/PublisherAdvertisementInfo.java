package pl.edu.agh.xp.advertisements.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublisherAdvertisementInfo {

    private String id;
    private String url;

    public static PublisherAdvertisementInfo mapFromAd(Advertisement advertisement) {
        return PublisherAdvertisementInfo.builder()
                .id(advertisement.getId().toString())
                .url(advertisement.getUrl())
                .build();
    }

}
