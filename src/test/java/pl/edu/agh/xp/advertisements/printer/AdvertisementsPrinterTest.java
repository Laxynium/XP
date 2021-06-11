package pl.edu.agh.xp.advertisements.printer;

import org.junit.jupiter.api.Test;
import pl.edu.agh.xp.advertisements.model.*;
import pl.edu.agh.xp.advertisements.service.advertisement.AdvertisementsPrinter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AdvertisementsPrinterTest {

    @Test
    void printNotNullAdvertisement_shouldAllBePrintedCorrectly() {
        // given
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var filePrinter = new AdvertisementsPrinter(printStream);

        var advertisement1 = Advertisement.builder()
                .id(1)
                .type(AdvertisementType.create("image"))
                .format(AdvertisementFormat.create("medium"))
                .advertiserMail("Example company")
                .price(Price.create("0.5 USD"))
                .priceType(PricingMethod.create("PER_VIEW"))
                .url("https://example.ads.com")
                .title("Title")
                .details("details")
                .build();

        var advertisement2 = Advertisement.builder()
                .id(2)
                .type(AdvertisementType.create("image"))
                .format(AdvertisementFormat.create("medium"))
                .advertiserMail("Example company")
                .price(Price.create("0.5 USD"))
                .priceType(PricingMethod.create("PER_VIEW"))
                .url("https://example.ads.com")
                .title("Title")
                .details("details")
                .build();

        var advertisements = List.of(advertisement1, advertisement2, advertisement1, advertisement2);
        // when
        filePrinter.print(advertisements);
        // then
        var actual = outContent.toString();
        var result = """
                |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
                |1|IMAGE|medium|Example company|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |2|IMAGE|medium|Example company|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |1|IMAGE|medium|Example company|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |2|IMAGE|medium|Example company|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                End of Advertisements
                """;
        assertThat(result).isEqualToIgnoringNewLines(actual);
    }

    @Test
    void printNullAdvertisement_shouldPrintEmptyTable() {
        // given
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var filePrinter = new AdvertisementsPrinter(printStream);

        var advertisements = List.<Advertisement>of();
        // when
        filePrinter.print(advertisements);
        // then
        var actual = outContent.toString();
        var result = """
                |ID|TYPE|FORMAT|ADVERTISER|PRICE|PRICE TYPE|URL|TITLE|DETAILS|
                End of Advertisements
                """;

        assertThat(result).isEqualToIgnoringNewLines(actual);
    }

}
