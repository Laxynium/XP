package pl.edu.agh.xp.advertisements.printer;

import java.io.*;
import java.util.stream.Stream;

class CSVFileReader implements FileReader {
    public Stream<String> readFile(String fileName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(fileName);
        BufferedReader bufferedInputFileReader = new BufferedReader(new InputStreamReader(inputStream));
        return getFileLines(bufferedInputFileReader);
    }

    public Stream<String> getFileLines(BufferedReader reader) {
        return reader.lines();
    }
}
