package pl.edu.agh.xp.advertisements.service.csv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVReaderTest {

    @TempDir
    File tempDir;

    @Test
    void read_shouldThrowAnException_whenWrongFileType() {
        Executable executable = () -> new CSVReader<>(Advertisement.class, true)
                .read(FileName.create("test.txt"));

        assertThrows(Exception.class, executable, "Wrong file format!");
    }

    @Test
    void read_shouldReturnListOfAdvertisements_whenCorrectInputGiven() throws IOException {
        // given
        var originalPath = Paths.get("src/test/resources/advertisements.csv");
        var data = new File(tempDir, "data.csv");
        Files.copy(originalPath, data.toPath(), StandardCopyOption.REPLACE_EXISTING);

        assertThat(data).exists();
        assertEquals(Files.readAllLines(originalPath), Files.readAllLines(data.toPath()));

        // when
        List<Advertisement> actual = new CSVReader<>(Advertisement.class, true)
                .read(FileName.create(data.getAbsolutePath()));

        // then
        assertEquals(Files.readAllLines(data.toPath()).size() - 1, actual.size());
    }

    @ParameterizedTest
    @MethodSource("incorrectFilenameInput")
    void read_shouldThrowAnException_whenFilenameIsNullOrEmpty(String filename) {
        // given

        // when
        Exception e = assertThrows(RuntimeException.class,
                () -> new CSVReader<>(Advertisement.class, true)
                        .read(FileName.create(filename)));

        // then
        assertEquals("Incorrect filename", e.getMessage());
    }

    private static Stream<Arguments> incorrectFilenameInput() {
        return Stream.of(
                Arguments.arguments((Object) null),
                Arguments.arguments("")
        );
    }

}