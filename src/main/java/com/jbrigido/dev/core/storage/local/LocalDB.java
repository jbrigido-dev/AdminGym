package com.jbrigido.dev.core.storage.local;

import com.jbrigido.dev.utilities.LocalStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDB {

    private static Connection connection;

    private LocalDB() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(LocalStorage.URL + LocalStorage.DATABASE, LocalStorage.USER, LocalStorage.PASS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
