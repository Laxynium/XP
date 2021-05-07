package pl.agh.xp.Advertisements.file_printer;

import java.io.*;
import java.util.stream.Stream;

public class FilePrinter {
    private PrintStream printStream;

    public FilePrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void print(String file_name) {
        File inputFile = new File(file_name);
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            BufferedReader bufferedInputFileReader = new BufferedReader(new InputStreamReader(inputStream));

            Stream<String> inputFileStreamWithoutDelimiter = bufferedInputFileReader.lines()
                    .map(line -> line.replaceAll(",", "\t"));

            inputFileStreamWithoutDelimiter.forEach(printStream::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


