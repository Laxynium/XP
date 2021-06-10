package pl.edu.agh.xp.advertisements.service.csv;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVWriter {

    public <T> void write(FileName filePath, T object) {
        var rowToWrite = convertToCsvRow(object);

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

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath.getValue(), true))) {
            if (newFile) {
                pw.println(createHeadersRow(object.getClass()));
            }
            pw.println(rowToWrite);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not been found.", e);
        }
    }

    public boolean delete(FileName filePath) {
        var file = new File(filePath.getValue());
        return file.delete();
    }

    private <T> String convertToCsvRow(T object) {
        var fields = Stream.of(object.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get") && !"getClass".equals(method.getName()))
                .map(method1 -> {
                    try {
                        return method1.invoke(object);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException();
                    }
                })
                .map(Object::toString)
                .collect(Collectors.toList());

        return "\"" + String.join("\",\"", fields) + "\"";
    }

    private <T> String createHeadersRow(Class<T> clazz) {
        var fields = Stream.of(clazz.getFields())
                .map(Field::getName)
                .collect(Collectors.toList());

        return "\"" + String.join("\",\"", fields) + "\"";
    }
}
