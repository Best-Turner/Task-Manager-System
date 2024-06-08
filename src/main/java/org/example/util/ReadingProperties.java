package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadingProperties {

    private final String pathToData;
    private Map<String, String> dataForConnection;

    public ReadingProperties(String pathToData) {
        this.pathToData = pathToData;
        dataForConnection = new HashMap<>();
    }

    public Map<String, String> getDataForConnection(String keyName, String keyPassword, String keyUrl) throws IOException {
        Properties properties = readDataForConnection();
        dataForConnection.put(keyName, properties.get(keyName).toString());
        dataForConnection.put(keyPassword, properties.get(keyPassword).toString());
        dataForConnection.put(keyUrl, properties.get(keyUrl).toString());
        return dataForConnection;
    }

    private Properties readDataForConnection() throws IOException {
        InputStream resourceAsStream = ReadingProperties.class.getClassLoader().getResourceAsStream(pathToData);
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return properties;
    }
}
