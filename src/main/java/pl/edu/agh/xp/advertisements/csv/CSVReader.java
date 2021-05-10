package pl.edu.agh.xp.advertisements.csv;

import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {

    static List<Advertisement> read(String fileName) {
        int index = fileName.lastIndexOf('.');

        if (index > 0) {
            String extension = fileName.substring(index + 1);
            if (!extension.equals("csv")) {
                throw new RuntimeException("Wrong file format!");
            }
        }

        BufferedReader csvReader;
        List<Advertisement> advertisements = new ArrayList<>();

        try {
            csvReader = new BufferedReader(new FileReader(fileName));
            String headers = csvReader.readLine();

            String row;
            Advertisement ad;

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                String row_data = Arrays.stream(data)
                        .map(s -> s.substring(1, s.length() - 1))
                        .collect(Collectors.joining(","));

                ad = new Advertisement(row_data.split(","));
                advertisements.add(ad);
            }

            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return advertisements;
    }

}