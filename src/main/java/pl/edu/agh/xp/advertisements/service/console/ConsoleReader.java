package pl.edu.agh.xp.advertisements.service.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader {

    private final BufferedReader reader;
    public ConsoleReader(InputStream inputStream) {
        this(new BufferedReader(new InputStreamReader(inputStream)));
    }

    public ConsoleReader(BufferedReader reader) {
        this.reader = reader;
    }

    public Integer readInteger(String message) {
        System.out.println(message);

        String line;
        try {
            line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }

    public String readString(String message) {
        System.out.println(message);

        String line;
        try {
            line = reader.readLine();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }
}
