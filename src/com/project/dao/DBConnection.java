package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connection Utility
 * Provides a reusable method to obtain a JDBC connection
 * to the MySQL database via XAMPP.
 *
 * Configuration:
 *   Host:     localhost
 *   Port:     3306
 *   Database: Student_Management_System
 *   User:     root
 *   Password: (empty)
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/Student_Management_System";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Returns a live Connection to the MySQL database.
     * The caller is responsible for closing the connection after use.
     *
     * @return java.sql.Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
