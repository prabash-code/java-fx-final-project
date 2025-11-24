package edu.icet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_management_system", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new DBConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
