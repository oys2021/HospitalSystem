package org.example;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecutePatientQueryTest {
    static Connection connection;
    static ExecutePatientQuery dao;

    @BeforeAll
    static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb2;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE Patient (PatientID INT AUTO_INCREMENT PRIMARY KEY, FirstName VARCHAR(255), LastName VARCHAR(255), Address VARCHAR(255), TelephoneNumber VARCHAR(255))");
        dao = new ExecutePatientQuery() {
            @Override
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
    }

    @AfterEach
    void clearTable() throws SQLException {
        connection.createStatement().execute("DELETE FROM Patient");
    }

    @AfterAll
    static void teardown() throws SQLException {
        connection.close();
    }

    @Test
    void testInsertAndGetAllPatients() throws SQLException {
        dao.insertPatient(new Patient("Alice", "Wonder", "Wonderland", "888888"));
        List<Patient> all = dao.getAllPatients();
        assertEquals(1, all.size());
        assertEquals("Alice", all.get(0).getFirstName());
    }

    @Test
    void testUpdatePatient() throws SQLException {
        dao.insertPatient(new Patient("Bob", "Marley", "Old House", "555555"));
        int id = dao.getAllPatients().get(0).getPatientID();
        dao.updatePatient(new Patient(id, "Bob", "Marley", "New House", "123123"));
        assertEquals("New House", dao.getAllPatients().get(0).getAddress());
    }

    @Test
    void testDeletePatient() throws SQLException {
        dao.insertPatient(new Patient("Charlie", "Chaplin", "London", "999999"));
        int id = dao.getAllPatients().get(0).getPatientID();
        dao.deletePatient(id);
        assertTrue(dao.getAllPatients().isEmpty());
    }

    @Test
    void testDeleteNonExistentPatient() {
        assertDoesNotThrow(() -> dao.deletePatient(9999));
    }

    @Test
    void testUpdateNonExistentPatient() {
        assertDoesNotThrow(() -> dao.updatePatient(new Patient(9999, "X", "Y", "Z", "000")));
    }
}
