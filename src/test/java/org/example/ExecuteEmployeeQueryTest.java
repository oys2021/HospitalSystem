package org.example;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteEmployeeQueryTest {
    static Connection connection;
    static ExecuteEmployeeQuery dao;

    @BeforeAll
    static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE Employee (EmployeeID INT AUTO_INCREMENT PRIMARY KEY, FirstName VARCHAR(255), SurName VARCHAR(255), Address VARCHAR(255), TelephoneNumber VARCHAR(255))");
        dao = new ExecuteEmployeeQuery(null) {
            @Override
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
    }

    @AfterEach
    void clearTable() throws SQLException {
        connection.createStatement().execute("DELETE FROM Employee");
    }

    @AfterAll
    static void teardown() throws SQLException {
        connection.close();
    }

    @Test
    void testInsertAndGetAll() throws SQLException {
        dao.insert(new Employee("John", "Doe", "123 Street", "123456"));
        List<Employee> all = dao.getAll();
        assertEquals(1, all.size());
        assertEquals("John", all.get(0).getFirstName());
    }

    @Test
    void testUpdateAddress() throws SQLException {
        dao.insert(new Employee("Jane", "Smith", "Old Addr", "111111"));
        int id = dao.getAll().get(0).getEmployeeID();
        dao.updateAddress(id, "New Addr");
        assertEquals("New Addr", dao.getAll().get(0).getAddress());
    }

    @Test
    void testDelete() throws SQLException {
        dao.insert(new Employee("Mark", "Lee", "Somewhere", "999999"));
        int id = dao.getAll().get(0).getEmployeeID();
        dao.delete(id);
        assertTrue(dao.getAll().isEmpty());
    }

    @Test
    void testUpdateNonExistentEmployee() throws SQLException {
        assertDoesNotThrow(() -> dao.updateAddress(9999, "No Address")); // Should not crash
    }

    @Test
    void testDeleteNonExistentEmployee() throws SQLException {
        assertDoesNotThrow(() -> dao.delete(9999)); // Should not crash
    }

}
