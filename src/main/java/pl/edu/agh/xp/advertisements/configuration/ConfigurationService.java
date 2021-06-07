package pl.edu.agh.xp.advertisements.configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigurationService {

    private final ObjectWriter writer;
    private final ObjectReader reader;

    public ConfigurationService() {
        var mapper = new JsonMapper();
        mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        writer = mapper.writerFor(AdvertisementConfiguration.class);
        reader = mapper.readerFor(AdvertisementConfiguration.class);
    }

    public void save(String path) {
        try {
            var resultFile = Path.of(path).toFile();
            if (resultFile.exists()) {
                resultFile.delete();
            }
            resultFile.createNewFile();
            writer.writeValue(resultFile, AdvertisementConfiguration.INSTANCE);
        } catch (IOException e) {
            throw new RuntimeException("Can't save configuration", e);
        }
    }

    public void read(String path) {
        try {
            var configurationFile = Path.of(path).toFile();
            if (configurationFile.exists()) {
                AdvertisementConfiguration.INSTANCE = reader.readValue(configurationFile, AdvertisementConfiguration.class);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read configuration", e);
        }
    }

}
