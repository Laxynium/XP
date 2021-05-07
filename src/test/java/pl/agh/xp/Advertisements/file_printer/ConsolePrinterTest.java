package pl.agh.xp.Advertisements.file_printer;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsolePrinterTest {
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
        ConsolePrinter consolePrinter = new ConsolePrinter();
        var file_name = "src/test/resources/file_to_print.csv";
        consolePrinter.print(file_name);

        var expected = "a\tb\tc\n" +
                "aa\tab\tac\n";
        var actual = outContent.toString();
        assertEquals(expected, actual);
    }

}
