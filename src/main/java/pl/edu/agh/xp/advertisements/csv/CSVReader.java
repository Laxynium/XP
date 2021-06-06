package pl.edu.agh.xp.advertisements.csv;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import pl.edu.agh.xp.advertisements.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader {
    private final ObjectReader advertisementReader;

    public CSVReader(){
        var mapper = new CsvMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        var schema = mapper.schemaFor(Advertisement.class).withHeader();
        advertisementReader =  mapper.readerFor(Advertisement.class).with(schema);
    }

    public List<Advertisement> read(FileName fileName) {
        try{
            if(!Files.exists(Path.of(fileName.getValue()))){
                return new ArrayList<>();
            }

            var fileContent = Files.readString(Path.of(fileName.getValue()));
            MappingIterator<Advertisement> it = advertisementReader
                    .readValues(fileContent);

            return it.readAll();
        }catch (IOException exception){
            throw new RuntimeException("Unable to read file", exception);
        }
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
        String[] split = rowData.split(",");
        return new Advertisement(Integer.parseInt(split[0]), AdvertisementType.create(split[1]), AdvertisementFormat.create(split[2]), split[3], Price.create(split[4]), PricingMethod.create(split[5]), split[6],split[7], split[8]);
    }
}