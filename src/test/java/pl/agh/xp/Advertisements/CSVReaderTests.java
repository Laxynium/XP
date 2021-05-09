import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CSVReaderTests {

    @Test
    void read_shouldThrowAnException_whenWrongFileType() {
        var fileName = "test.txt";

        Executable executable = () -> CSVReader.read(fileName);

        Assertions.assertThrows(Exception.class, executable, "Wrong file format!");
    }

}
