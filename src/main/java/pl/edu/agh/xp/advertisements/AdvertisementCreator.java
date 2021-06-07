package pl.edu.agh.xp.advertisements;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.model.*;

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

        return new Advertisement(id, AdvertisementType.create(type), AdvertisementFormat.create(format), advertiser, Price.create(price), PricingMethod.create(priceType), url, title, details);
    }

}
