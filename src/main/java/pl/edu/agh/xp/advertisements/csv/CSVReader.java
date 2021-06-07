package pl.edu.agh.xp.advertisements.csv;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import pl.edu.agh.xp.advertisements.model.Advertisement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private final ObjectReader advertisementReader;

    public CSVReader() {
        var mapper = new CsvMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        var schema = mapper.schemaFor(Advertisement.class).withHeader();
        advertisementReader = mapper.readerFor(Advertisement.class).with(schema);
    }

    public List<Advertisement> read(FileName fileName) {
        try {
            if (!Files.exists(Path.of(fileName.getValue()))) {
                return new ArrayList<>();
            }

            var fileContent = Files.readString(Path.of(fileName.getValue()));
            MappingIterator<Advertisement> it = advertisementReader
                    .readValues(fileContent);

            return it.readAll();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read file", exception);
        }
    }

}