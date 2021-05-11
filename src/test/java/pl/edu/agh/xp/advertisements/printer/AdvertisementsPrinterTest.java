package pl.edu.agh.xp.advertisements.printer;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class AdvertisementsPrinterTest {

    @Test
    void printNotNullAdvertisement_shouldAllBePrintedCorrectly() {
        // given
        var outContent = new ByteArrayOutputStream();
        var printStream = new PrintStream(outContent);
        var filePrinter = new AdvertisementsPrinter(printStream);

        var advertisement1 = Advertisement.builder()
                .id(1)
                .type("image")
                .format("medium")
                .advertiser("Example comapany")
                .price("0.5 USD")
                .priceType("PER_VIEW")
                .url("https://example.ads.com")
                .title("Title")
                .details("details")
                .build();

        var advertisement2 = Advertisement.builder()
                .id(1)
                .type("image")
                .format("medium")
                .advertiser("Example comapany")
                .price("0.5 USD")
                .priceType("PER_VIEW")
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
                |1|image|medium|Example comapany|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |1|image|medium|Example comapany|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |1|image|medium|Example comapany|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                |1|image|medium|Example comapany|0.5 USD|PER_VIEW|https://example.ads.com|Title|details|
                End of Advertisements
                """;
        assertEquals(result, actual);
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

        assertEquals(result, actual);

    }

}
