package org.example;

import Config.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ExecutePatientQuery {
    DatabaseConfig databaseConfig;

    public ExecutePatientQuery() {
        this.databaseConfig = new DatabaseConfig();
    }


    public void insertPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO Patient (FirstName, LastName, Address, TelephoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getTelephoneNumber());
            stmt.executeUpdate();
        }
    }


    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Connection conn = databaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("PatientID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("TelephoneNumber")
                );
                patients.add(patient);
            }
        }
        return patients;
    }


    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE Patient SET FirstName = ?, LastName = ?, Address = ?, TelephoneNumber = ? WHERE PatientID = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setString(3, patient.getAddress());
            stmt.setString(4, patient.getTelephoneNumber());
            stmt.setInt(5, patient.getPatientID());
            stmt.executeUpdate();
        }
    }


    public void deletePatient(int patientID) throws SQLException {
        String sql = "DELETE FROM Patient WHERE PatientID = ?";
        try (Connection conn = databaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            stmt.executeUpdate();
        }
    }


    public abstract Connection getConnection() throws SQLException;
}
