package pl.edu.agh.xp.advertisements.printer;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public interface FileReader {
    Stream<String> readFile(String fileName) throws FileNotFoundException;
}
