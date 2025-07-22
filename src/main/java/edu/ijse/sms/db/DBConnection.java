package edu.ijse.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sms_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("JDBC Driver not found", e);
            }
        }
        return connection;
    }
}

