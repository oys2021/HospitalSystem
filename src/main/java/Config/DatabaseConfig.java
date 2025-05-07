package Config;

import java.sql.*;
import java.util.logging.*;

public class DatabaseConfig {
    private static final Logger logger = Logger.getLogger(DatabaseConfig.class.getName());

    public static final String URL = "jdbc:postgresql://localhost:5432/Hospital";
    public static final String Username ="postgres";
    public static final String Password="admin1234";
    Connection connection;

    public Connection getConnection() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL,Username,Password);
        } catch (ClassNotFoundException e){
            logger.log(Level.SEVERE, "Error connecting to the PostgreSQL database", e);
            throw new SQLException("JDBC Driver not found",e);
        }
        return connection;
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection to PostgreSQL database successful!");
                return true;
            } else {
                System.out.println("Failed to make connection!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return false;
        }
    }
}
