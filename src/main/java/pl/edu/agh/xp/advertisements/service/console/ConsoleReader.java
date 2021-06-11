package pl.edu.agh.xp.advertisements.service.console;

import java.io.*;

public class ConsoleReader {

    private final BufferedReader reader;
    private final PrintStream outputStream;

    public ConsoleReader(InputStream inputStream, PrintStream outputStream) {
        this(new BufferedReader(new InputStreamReader(inputStream)), outputStream);
    }

    public ConsoleReader(BufferedReader reader, PrintStream outputStream) {
        this.reader = reader;
        this.outputStream = outputStream;
    }

    public Integer readInteger(String message) {
        outputStream.println(message);

        String line;
        try {
            line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }

    public String readString(String message) {
        outputStream.println(message);

        String line;
        try {
            line = reader.readLine();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }
}
