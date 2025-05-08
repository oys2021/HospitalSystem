SELECT * FROM information_schema.tables;

CREATE TABLE Employee (
EmployeeID SERIAL Primary Key,
FirstName VARCHAR(20) NOT NULL,
SurName VARCHAR(20) NOT NULL,
Address Text,
TelephoneNumber VARCHAR(20) 
);

SELECT * FROM Patient;

CREATE TABLE Doctor (
    EmployeeID INTEGER PRIMARY KEY REFERENCES Employee(EmployeeID),
    Specialty VARCHAR(100)
);

CREATE TABLE Department (
DepartmentCode VARCHAR(20) PRIMARY KEY,
Name VARCHAR(100) NOT NULL,
Building VARCHAR(100),
DirectorID INTEGER REFERENCES Doctor(EmployeeID) 
);

CREATE TABLE Nurse (
    EmployeeID INTEGER PRIMARY KEY REFERENCES Employee(EmployeeID),
    Rotation VARCHAR(50),
    Salary NUMERIC(10, 2),
    DepartmentCode VARCHAR(10) REFERENCES Department(DepartmentCode)
);


CREATE TABLE Ward (
    DepartmentCode VARCHAR(10),
    WardNumber SERIAL,
    NumberOfBeds INTEGER,
    SupervisorID INTEGER REFERENCES Nurse(EmployeeID), 
    PRIMARY KEY (DepartmentCode, WardNumber),
    FOREIGN KEY (DepartmentCode) REFERENCES Department(DepartmentCode)
);

CREATE TABLE Patient (
    PatientID SERIAL PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address TEXT,
    TelephoneNumber VARCHAR(20)
);

CREATE TABLE Hospitalization (
    HospitalizationID SERIAL PRIMARY KEY,
    PatientID INTEGER NOT NULL REFERENCES Patient(PatientID), 
    DepartmentCode VARCHAR(10) NOT NULL,
    WardNumber INTEGER NOT NULL,
    BedNumber INTEGER NOT NULL,
    Diagnosis TEXT,
    TreatedByDoctorID INTEGER REFERENCES Doctor(EmployeeID), 

    FOREIGN KEY (DepartmentCode, WardNumber) REFERENCES Ward(DepartmentCode, WardNumber)
);
