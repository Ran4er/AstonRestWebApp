package ru.aston.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String FILENAME = "database.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(FILENAME)) {
            properties.load(inputStream);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
