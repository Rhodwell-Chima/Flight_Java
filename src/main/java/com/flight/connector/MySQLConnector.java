
package com.flight.connector;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The MySQLConnector class provides methods to establish and manage a connection to a MySQL database.
 * Example usage:
 * <pre>
 * {@code
 * Connection connection = MySQLConnector.getConnection();
 * // Use the connection
 * MySQLConnector.closeConnection();
 * }
 * </pre>
 *
 * Note: Ensure that the database credentials are properly set.
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>io.github.cdimascio:java-dotenv:5.2.2</li>
 *   <li>mysql:mysql-connector-java:8.0.33</li>
 * </ul>
 *
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see io.github.cdimascio.dotenv.Dotenv
 */

public class MySQLConnector {
    private static final Logger LOGGER = Logger.getLogger(MySQLConnector.class.getName());
    private static final Dotenv dotenv = Dotenv.load();
    private static final String HOST_URL = dotenv.get("DB_HOST_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    private static Connection connection;

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object to the MySQL database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            if (HOST_URL == null || USER == null || PASSWORD == null) {
                throw new SQLException("Database credentials are not set properly.");
            }
            connection = DriverManager.getConnection(HOST_URL, USER, PASSWORD);
        }
        return connection;
    }

    /**
     * Closes the connection to the MySQL database if it is open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to close the database connection.", e);
            }
        }
    }
}