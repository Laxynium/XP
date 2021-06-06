package pl.edu.agh.xp.advertisements.csv;

import lombok.Value;

@Value
public class FileName {
    String value;
    private FileName(String value){
        this.value = value;
    }
    public static FileName create(String value){
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Incorrect filename");
        }
        validateExtension(value, "csv");
        return new FileName(value);
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
}
