package pl.edu.agh.xp.advertisements.service.configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigurationService {

    private static final String configurationFileDefaultLocation = "configuration.json";

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
            var parent = resultFile.getParentFile();
            if(!parent.exists()){
                parent.mkdirs();
            }
            resultFile.createNewFile();
            writer.writeValue(resultFile, AdvertisementConfiguration.INSTANCE);
        } catch (IOException e) {
            throw new RuntimeException("Can't save configuration", e);
        }
    }

    public void read() {
        try {
            var configurationFile = Path.of(configurationFileDefaultLocation).toFile();
            if (configurationFile.exists()) {
                AdvertisementConfiguration.INSTANCE = reader.readValue(configurationFile, AdvertisementConfiguration.class);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read configuration", e);
        }
    }

}
