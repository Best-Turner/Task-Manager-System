package org.example.db;

import org.example.util.ReadingProperties;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseConnection implements ConnectionManager {
    private final static String KEY_USER_NAME = "name";
    private final static String KEY_PASSWORD = "password";
    private final static String KEY_URL = "url";
    private final ReadingProperties readingProperties;
    private Connection connection;

    public DatabaseConnection(ReadingProperties readingProperties) {
        this.readingProperties = readingProperties;
    }

    @Override
    public Connection getConnection() throws SQLException, IOException {
        Map<String, String> dataForConnection =
                readingProperties.getDataForConnection(KEY_USER_NAME, KEY_PASSWORD, KEY_URL);
        return DriverManager
                .getConnection(dataForConnection.get(KEY_URL),
                        dataForConnection.get(KEY_USER_NAME),
                        dataForConnection.get(KEY_PASSWORD));
    }

}
