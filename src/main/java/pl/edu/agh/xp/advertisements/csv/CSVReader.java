package pl.edu.agh.xp.advertisements.csv;

import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    public List<Advertisement> read(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Incorrect filename");
        }
        validateExtension(fileName, "csv");

        List<Advertisement> advertisements = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            skipHeadersRow(csvReader);

            String row;
            while ((row = csvReader.readLine()) != null) {
                advertisements.add(readAdvertisementFromRow(row));
            }
        }
        catch (FileNotFoundException e){
            return new ArrayList<>();
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to read file");
        }

        return advertisements;
    }

    private static void validateExtension(String fileName, String extension) {
        int index = fileName.lastIndexOf('.');

        if (index > 0) {
            String ext = fileName.substring(index + 1);
            if (!ext.equals(extension)) {
                throw new RuntimeException("Wrong file format!");
            }
        }
    }

    private static void skipHeadersRow(BufferedReader csvReader) throws IOException {
        csvReader.readLine();
    }

    private static Advertisement readAdvertisementFromRow(String row) {
        String[] data = row.split(",");
        String rowData = Arrays.stream(data)
                .map(s -> s.substring(1, s.length() - 1))
                .collect(Collectors.joining(","));

        return new Advertisement(rowData.split(","));
    }
}