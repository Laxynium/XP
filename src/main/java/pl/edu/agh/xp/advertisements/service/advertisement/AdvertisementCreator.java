package pl.edu.agh.xp.advertisements.service.advertisement;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.xp.advertisements.model.*;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

@RequiredArgsConstructor
public class AdvertisementCreator {

    private final ConsoleReader reader;

    public Advertisement createFromConsole() {

        var id = reader.readInteger("Please enter id:");
        var type = reader.readString("Please enter type:");
        var format = reader.readString("Please enter format:");
        var advertiser = reader.readString("Please enter advertiser:");
        var price = reader.readString("Please enter price:");
        var priceType = reader.readString("Please enter price type:");
        var url = reader.readString("Please enter url:");
        var title = reader.readString("Please enter title:");
        var details = reader.readString("Please enter details:");

        return Advertisement.builder()
                .id(id)
                .type(AdvertisementType.create(type))
                .format(AdvertisementFormat.create(format))
                .advertiserMail(advertiser)
                .price(Price.create(price))
                .priceType(PricingMethod.create(priceType))
                .url(url)
                .title(title)
                .details(details)
                .build();
    }

}
