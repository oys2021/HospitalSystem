package org.example;

import Config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig dbConfig = new DatabaseConfig();

        // Test the database connection
        boolean isConnected = dbConfig.testConnection();

        if (isConnected) {
            System.out.println("Database connection is active.");
        } else {
            System.out.println("Failed to connect to the database.");
        }

    }
}