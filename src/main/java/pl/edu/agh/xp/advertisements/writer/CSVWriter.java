package pl.edu.agh.xp.advertisements.writer;

import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.*;
import java.util.Arrays;

public class CSVWriter {

    private static final String headersRow = "\"id\",\"type\",\"format\",\"advertiser\",\"price\",\"price_type\",\"url\",\"title\",\"details\"";

    public void write(String filePath, Advertisement advertisement) {
        var rowToWrite = convertToCsvRow(advertisement);

        var newFile = false;
        var fileToWrite = new File(filePath);
        if (!fileToWrite.exists()) {
            try {
                newFile = fileToWrite.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Couldn't create a file.", e);
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath, true))) {
            if (newFile) {
                pw.println(headersRow);
            }
            pw.println(rowToWrite);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not been found.", e);
        }
    }

    private String convertToCsvRow(Advertisement advertisement) {
        var fields = Arrays.asList(
                advertisement.getId().toString(),
                advertisement.getType(),
                advertisement.getFormat(),
                advertisement.getAdvertiserMail(),
                advertisement.getPrice(),
                advertisement.getPriceType(),
                advertisement.getUrl(),
                advertisement.getTitle(),
                advertisement.getDetails()
        );

        return "\"" + String.join("\",\"", fields) + "\"";
    }

    public void delete(String filePath) {
        var file = new File(filePath);

        file.delete();
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath, true))) {
            file.createNewFile();
            pw.println(headersRow);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file.", e);
        }
    }
}
