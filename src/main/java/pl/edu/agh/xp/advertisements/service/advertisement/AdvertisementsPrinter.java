package pl.edu.agh.xp.advertisements.service.advertisement;

import lombok.AllArgsConstructor;
import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.PrintStream;
import java.util.List;

@AllArgsConstructor
public class AdvertisementsPrinter {

    private final PrintStream printStream;

    public void print(List<Advertisement> advertisements) {
        printHeader();
        advertisements.forEach(this::printAdvertisement);
        printFooter();
    }

    private void printHeader() {
        printStream.format("|%s|%s|%s|%s|%s|%s|%s|%s|%s|\n", "ID", "TYPE", "FORMAT", "ADVERTISER", "PRICE", "PRICE TYPE", "URL", "TITLE", "DETAILS");
    }

    private void printAdvertisement(Advertisement a) {
        if (a != null) {
            printStream.format("|%s|%s|%s|%s|%s|%s|%s|%s|%s|\n", a.getId(), a.getType(), a.getFormat(), a.getAdvertiserMail(), a.getPrice(), a.getPriceType(), a.getUrl(), a.getTitle(), a.getDetails());
        }
    }

    private void printFooter() {
        printStream.println("End of Advertisements");
    }
}

