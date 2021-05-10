package pl.edu.agh.xp.advertisements.printer;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FilePrinter {
    private final PrintStream printStream;
    private final pl.edu.agh.xp.advertisements.printer.FileReader fileReader;

    public FilePrinter(PrintStream printStream, FileReader fileReader) {
        this.printStream = printStream;
        this.fileReader = fileReader;
    }

    public void print(String fileName, Supplier<Function<String, String >> lineConverter) {
        try {
            Stream<String> inputFileStream = fileReader.readFile(fileName);
            inputFileStream.map(lineConverter.get()).forEach(printStream::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class CSVLineConverter {
    public Function<String, String> convertLine() {
        return line -> line.replaceAll(",", "\t");

    }
}


