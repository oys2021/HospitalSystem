package org.example;


public class Employee {
    private int employeeID;
    private String firstName;
    private String surName;
    private String address;
    private String telephone;

    public Employee() {

    }

    public Employee(String firstName, String surName, String address, String telephone) {
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.telephone = telephone;
    }

    public Employee(int employeeID, String firstName, String surName, String address, String telephone) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.telephone = telephone;
    }

    // Getters and Setters
    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + employeeID +
                ", Name='" + firstName + " " + surName + '\'' +
                ", Address='" + address + '\'' +
                ", Telephone='" + telephone + '\'' +
                '}';
    }
}
