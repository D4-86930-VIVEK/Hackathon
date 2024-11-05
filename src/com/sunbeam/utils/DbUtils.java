package com.sunbeam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// util class for connecting to JDBC
public class DbUtils {
    private final static String URL = "jdbc:mysql://localhost:3306/pizza";
    private final static String USERNAME = "D4_86930_Vivek";
    private final static String PASS = "manager";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASS);
    }
}
