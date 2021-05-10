package pl.agh.xp.Advertisements.file_printer;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public interface FileReader {
    Stream<String> readFile(String fileName) throws FileNotFoundException;
}
