package pl.edu.agh.xp.advertisements.service.advertisement;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.model.*;
import pl.edu.agh.xp.advertisements.service.console.ConsoleReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class AdvertisementCreator {

    private final ConsoleReader reader;

    public Advertisement createFromConsole() {

        var id = reader.readInteger("Please enter id: (number)");

        var types = listToNumberedMap(AdvertisementConfiguration.INSTANCE.availableAdvertisementTypes);
        var type = AdvertisementType.create(types.get(reader.readInteger("Please enter type by number: (Available types are " + types.entrySet() + ")")));

        var formats = listToNumberedMap(AdvertisementConfiguration.INSTANCE.availableAdvertisementFormats);
        var format = AdvertisementFormat.create(formats.get(reader.readInteger("Please enter format by number: (Available formats are " + formats.entrySet() + ")")));

        var advertiser = reader.readString("Please enter advertiser email: (email)");

        var currencies = Arrays.toString(AdvertisementConfiguration.INSTANCE.availableCurrencies.toArray());
        var price = Price.create(reader.readString("Please enter price in format \"number currency\" for example 1.0 USD: (Available currencies are " + currencies + ")"));

        var pricingMethods = listToNumberedMap(AdvertisementConfiguration.INSTANCE.availablePricingMethods);
        var priceType = PricingMethod.create(pricingMethods.get(reader.readInteger("Please enter price type by number: (Available pricing methods are " + pricingMethods.entrySet() + ")")));

        var url = reader.readString("Please enter url: (URL)");
        var title = reader.readString("Please enter title: (string)");
        var details = reader.readString("Please enter details: (string)");

        Advertisement buildedAd = Advertisement.builder()
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
        return buildedAd;
    }

    private HashMap<Integer, String> listToNumberedMap(List<String> list) {
        var map = new HashMap<Integer, String>();
        for (int i = 0; i < list.size(); i++) {
            map.put(i, list.get(i));
        }
        return map;
    }

}
