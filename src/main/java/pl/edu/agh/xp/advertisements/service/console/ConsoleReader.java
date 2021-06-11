package pl.edu.agh.xp.advertisements.service.console;

import java.io.*;

public class ConsoleReader {

    private final BufferedReader reader;
    private final PrintStream printer;
    public ConsoleReader(InputStream inputStream, PrintStream printStream) {
        this(new BufferedReader(new InputStreamReader(inputStream)), printStream);
    }

    public ConsoleReader(BufferedReader reader, PrintStream printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public Integer readInteger(String message) {
        printer.println(message);

        String line;
        try {
            line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException | IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }

    public String readString(String message) {
        printer.println(message);

        String line;
        try {
            line = reader.readLine();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("Incorrect input");
        }
    }
}
