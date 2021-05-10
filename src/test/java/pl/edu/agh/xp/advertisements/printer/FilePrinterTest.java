package pl.edu.agh.xp.advertisements.printer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilePrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printFileTest() {
        PrintStream printStream = new PrintStream(System.out);
        FileReader fileReader = new CSVFileReader();
        FilePrinter filePrinter = new FilePrinter(printStream, fileReader);
        var file_name = "src/test/resources/file_to_print.csv";
        CSVLineConverter csvLineConverter = new CSVLineConverter();
        filePrinter.print(file_name, csvLineConverter::convertLine);

        var expected = "a\tb\tc\n" +
                "aa\tab\tac\n";
        var actual = outContent.toString();
        assertEquals(expected, actual);
    }

}
