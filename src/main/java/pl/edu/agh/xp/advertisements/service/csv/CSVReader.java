package pl.edu.agh.xp.advertisements.service.csv;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVReader<T> {

    private final ObjectReader objectReader;

    public CSVReader(Class<T> typeParameterClass, boolean withHeader) {
        var mapper = new CsvMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        var schema = withHeader
                ? mapper.schemaFor(typeParameterClass).withHeader()
                : mapper.schemaFor(typeParameterClass).withoutHeader();
        objectReader = mapper.readerFor(typeParameterClass).with(schema);
    }

    public List<T> read(FileName fileName) {
        try {
            if (!Files.exists(Path.of(fileName.getValue()))) {
                return new ArrayList<>();
            }

            var fileContent = Files.readString(Path.of(fileName.getValue()));
            MappingIterator<T> it = objectReader.readValues(fileContent);

            return it.readAll();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read file", exception);
        }
    }

}