package pl.edu.agh.xp.advertisements;

import pl.edu.agh.xp.advertisements.console.ConsoleReader;
import pl.edu.agh.xp.advertisements.model.Advertisement;

public class AdvertisementCreator {

    public Advertisement createFromConsole(ConsoleReader reader) {

        try {
            Integer id = reader.readInteger("Please enter id:");
            String type = reader.readString("Please enter type:");
            String format = reader.readString("Please enter format:");
            String advertiser = reader.readString("Please enter advertiser:");
            String price = reader.readString("Please enter price:");
            String price_type = reader.readString("Please enter price type:");
            String url = reader.readString("Please enter url:");
            String title = reader.readString("Please enter title:");
            String details = reader.readString("Please enter details:");

            return new Advertisement(id, type, format, advertiser, price, price_type, url, title, details);
        } catch (RuntimeException e) {
            return null;
        }
    }

}
