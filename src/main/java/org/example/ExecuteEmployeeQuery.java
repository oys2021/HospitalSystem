package org.example;


import Config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ExecuteEmployeeQuery {
    DatabaseConfig databaseConfig;


    public ExecuteEmployeeQuery(Connection conn) {
        this.databaseConfig = new DatabaseConfig();
    }

    public void insert(Employee emp) throws SQLException {
        String sql = "INSERT INTO Employee (FirstName, SurName, Address, TelephoneNumber) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getSurName());
            stmt.setString(3, emp.getAddress());
            stmt.setString(4, emp.getTelephone());
            stmt.executeUpdate();
        }
    }

    public List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";
        List<Employee> list = new ArrayList<>();
        try (   Connection conn = databaseConfig.getConnection();
                Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("SurName"),
                        rs.getString("Address"),
                        rs.getString("TelephoneNumber")
                ));
            }
        }
        return list;
    }

    public void updateAddress(int id, String newAddress) throws SQLException {
        String sql = "UPDATE Employee SET Address = ? WHERE EmployeeID = ?";
        try (
                Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newAddress);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
        try (   Connection conn = databaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public abstract Connection getConnection() throws SQLException;
}

