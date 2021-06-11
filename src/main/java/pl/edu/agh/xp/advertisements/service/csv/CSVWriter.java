package pl.edu.agh.xp.advertisements.service.csv;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CSVWriter<T> {

    private final ObjectWriter objectWriter;

    public CSVWriter(Class<T> typeParameterClass, boolean withHeader) {
        var mapper = getObjectMapper();
        var schema = withHeader
                ? mapper.schemaFor(typeParameterClass).withHeader()
                : mapper.schemaFor(typeParameterClass).withoutHeader();
        this.objectWriter = mapper.writerFor(typeParameterClass).with(schema);
    }

    public void write(FileName filePath, T object) {
        boolean newFile = createFileIfNotExists(filePath);

        try (FileOutputStream os = new FileOutputStream(filePath.getValue(), true)) {
            var writer = objectWriter;
            if (!newFile) {
                var mapper = getObjectMapper();
                var schema = mapper.schemaFor(object.getClass()).withoutHeader();
                writer = mapper.writerFor(object.getClass()).with(schema);
            }
            writer.writeValues(os).write(object);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not been found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't open a file.", e);
        }
    }

    public boolean delete(FileName filePath) {
        var file = new File(filePath.getValue());
        return file.delete();
    }

    private CsvMapper getObjectMapper() {
        var mapper = new CsvMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);

        return mapper;
    }

    private boolean createFileIfNotExists(FileName filePath) {
        var newFile = false;
        var fileToWrite = new File(filePath.getValue());
        if (!fileToWrite.exists()) {
            try {
                var parent = fileToWrite.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                newFile = fileToWrite.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Couldn't create a file.", e);
            }
        }
        return newFile;
    }

}
