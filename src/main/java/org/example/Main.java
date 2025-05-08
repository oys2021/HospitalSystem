package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExecutePatientQuery patientDao = new ExecutePatientQuery() {
            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }
        };
        ExecuteEmployeeQuery employeeDao = new ExecuteEmployeeQuery(null) {
            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }
        };
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Patient Management");
            System.out.println("2. Employee Management");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1 -> patientMenu(patientDao, scanner);
                case 2 -> employeeMenu(employeeDao, scanner);
                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void patientMenu(ExecutePatientQuery dao, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Telephone: ");
                        String phone = scanner.nextLine();

                        Patient newPatient = new Patient(firstName, lastName, address, phone);
                        dao.insertPatient(newPatient);
                        System.out.println("Patient added.");
                    }
                    case 2 -> {
                        List<Patient> patients = dao.getAllPatients();
                        for (Patient p : patients) {
                            System.out.println(p.getPatientID() + ": " +
                                    p.getFirstName() + " " +
                                    p.getLastName() + ", " +
                                    p.getAddress() + ", " +
                                    p.getTelephoneNumber());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Patient ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New First Name: ");
                        String fn = scanner.nextLine();
                        System.out.print("New Last Name: ");
                        String ln = scanner.nextLine();
                        System.out.print("New Address: ");
                        String addr = scanner.nextLine();
                        System.out.print("New Telephone: ");
                        String tel = scanner.nextLine();

                        Patient updated = new Patient(id, fn, ln, addr, tel);
                        dao.updatePatient(updated);
                        System.out.println("Patient updated.");
                    }
                    case 4 -> {
                        System.out.print("Enter Patient ID to delete: ");
                        int id = scanner.nextInt();
                        dao.deletePatient(id);
                        System.out.println("Patient deleted.");
                    }
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void employeeMenu(ExecuteEmployeeQuery dao, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Address");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("First Name: ");
                        String fn = scanner.nextLine();
                        System.out.print("Surname: ");
                        String sn = scanner.nextLine();
                        System.out.print("Address: ");
                        String addr = scanner.nextLine();
                        System.out.print("Telephone: ");
                        String tel = scanner.nextLine();

                        dao.insert(new Employee(fn, sn, addr, tel));
                        System.out.println("Employee added.");
                    }
                    case 2 -> {
                        List<Employee> list = dao.getAll();
                        for (Employee emp : list) {
                            System.out.println(emp);
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Employee ID to update address: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("New Address: ");
                        String addr = scanner.nextLine();
                        dao.updateAddress(id, addr);
                        System.out.println("Address updated.");
                    }
                    case 4 -> {
                        System.out.print("Enter Employee ID to delete: ");
                        int id = scanner.nextInt();
                        dao.delete(id);
                        System.out.println("Employee deleted.");
                    }
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}
