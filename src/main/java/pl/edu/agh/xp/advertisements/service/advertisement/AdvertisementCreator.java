package pl.edu.agh.xp.advertisements.service.advertisement;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.model.*;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.util.Arrays;

@RequiredArgsConstructor
public class AdvertisementCreator {

    private final ConsoleReader reader;

    public Advertisement createFromConsole() {

        var id = reader.readInteger("Please enter id: (number)");

        var types = Arrays.toString(AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes.toArray());
        var type = AdvertisementType.create(reader.readString("Please enter type: (Available types are " + types + ")"));

        var formats = Arrays.toString(AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats.toArray());
        var format = AdvertisementFormat.create(reader.readString("Please enter format:(Available formats are " + formats + ")"));
        var advertiser = reader.readString("Please enter advertiser email: (email)");

        var currencies = Arrays.toString(AdvertisementConfiguration.INSTANCE.availableCurrencies.toArray());
        var price = Price.create(reader.readString("Please enter price: (Available currencies are " + currencies + ")"));

        var pricingMethods = Arrays.toString(AdvertisementConfiguration.INSTANCE.availablePricingMethods.toArray());
        var priceType = PricingMethod.create(reader.readString("Please enter price type: (Available pricing methods are " + pricingMethods + ")"));
        var url = reader.readString("Please enter url: (URL)");
        var title = reader.readString("Please enter title: (string)");
        var details = reader.readString("Please enter details: (string)");

        return Advertisement.builder()
                .id(id)
                .type(type)
                .format(format)
                .advertiserMail(advertiser)
                .price(price)
                .priceType(priceType)
                .url(url)
                .title(title)
                .details(details)
                .build();
    }

}
