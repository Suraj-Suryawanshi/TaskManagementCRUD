package com.surya.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	 private static final String URL = "jdbc:postgresql://10.1.1.53:5432/TRAINING";
	 private static final String USERNAME = "postgres";
	 private static final String PASSWORD = "M0b1cule!";
    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}