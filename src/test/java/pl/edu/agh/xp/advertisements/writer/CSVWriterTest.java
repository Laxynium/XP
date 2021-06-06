package pl.edu.agh.xp.advertisements.writer;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.xp.advertisements.csv.FileName;
import pl.edu.agh.xp.advertisements.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVWriterTest {

    @TempDir
    File tempDir;

    @ParameterizedTest
    @MethodSource("correctAdvertisementInput")
    void write_shouldCorrectlyAppendRowToFile_whenFileAlreadyExists(Advertisement advertisement, String row) throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/advertisements.csv");
        var data = new File(tempDir, "data.csv");
        Files.copy(originalPath, data.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(data).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(data.toPath()));

        var sut = new CSVWriter();

        // when
        sut.write(FileName.create(data.getAbsolutePath()), advertisement);
        var allLines = Files.readAllLines(data.toPath());

        // then
        assertEquals(Files.readAllLines(originalPath).size() + 1, allLines.size());
        assertEquals(row, allLines.get(allLines.size() - 1));
    }

    @ParameterizedTest
    @MethodSource("correctAdvertisementInput")
    void write_shouldCreateNewFileWithHeadersRow_whenFileDoesNotExist(Advertisement advertisement, String row) throws IOException {
        // given
        var data = new File(tempDir, "data.csv");
        assertThat(data).doesNotExist();

        var sut = new CSVWriter();

        // when
        sut.write(FileName.create(data.getAbsolutePath()), advertisement);

        // then
        assertThat(data).exists();
        var allLines = Files.readAllLines(data.toPath());

        assertEquals(2, allLines.size());
        assertEquals(row, allLines.get(allLines.size() - 1));
    }

    private static Stream<Arguments> correctAdvertisementInput() {
        return Stream.of(
                Arguments.arguments(
                        new Advertisement(1, AdvertisementType.create("type1"), AdvertisementFormat.create("format1"), "advertiser1", Price.create("1.0 USD"), PricingMethod.create("price_type1"), "url1", "title1", "details1"),
                        "\"1\",\"type1\",\"format1\",\"advertiser1\",\"1 USD\",\"price_type1\",\"url1\",\"title1\",\"details1\""
                ),
                Arguments.arguments(
                        new Advertisement(2, AdvertisementType.create("type2"), AdvertisementFormat.create("format2"), "advertiser2", Price.create("2.0 USD"), PricingMethod.create("price_type2"), "url2", "title2", "details2"),
                        "\"2\",\"type2\",\"format2\",\"advertiser2\",\"2 USD\",\"price_type2\",\"url2\",\"title2\",\"details2\""
                )
        );
    }

}
