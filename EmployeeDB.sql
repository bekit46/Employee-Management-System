CREATE DATABASE EmployeeDB;
USE EmployeeDB;

CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('MANAGER', 'ENGINEER', 'TECHNICIAN', 'INTERN') NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phone_no VARCHAR(15),
    dateofbirth DATE NOT NULL,
    dateofstart DATE NOT NULL,
    email VARCHAR(100)
);

INSERT INTO Employees (username, password, role, name, surname, phone_no, dateofbirth, dateofstart, email)
VALUES
('john_doe', 'password123', 'MANAGER', 'John', 'Doe', '1234567890', '1980-01-15', '2010-05-10', 'john.doe@example.com'),
('jane_smith', 'password123', 'ENGINEER', 'Jane', 'Smith', '1234567891', '1985-03-22', '2015-07-01', 'jane.smith@example.com'),
('robert_brown', 'password123', 'TECHNICIAN', 'Robert', 'Brown', '1234567892', '1990-07-11', '2020-09-15', 'robert.brown@example.com'),
('lisa_white', 'password123', 'INTERN', 'Lisa', 'White', '1234567893', '1998-12-02', '2023-01-01', 'lisa.white@example.com'),
('paul_green', 'password123', 'ENGINEER', 'Paul', 'Green', '1234567894', '1992-06-05', '2018-08-20', 'paul.green@example.com'),
('emily_black', 'password123', 'MANAGER', 'Emily', 'Black', '1234567895', '1983-10-18', '2008-04-12', 'emily.black@example.com'),
('david_gray', 'password123', 'TECHNICIAN', 'David', 'Gray', '1234567896', '1987-09-30', '2013-02-25', 'david.gray@example.com'),
('sarah_brown', 'password123', 'INTERN', 'Sarah', 'Brown', '1234567897', '2000-11-21', '2022-06-15', 'sarah.brown@example.com'),
('chris_blue', 'password123', 'ENGINEER', 'Chris', 'Blue', '1234567898', '1993-01-01', '2017-03-10', 'chris.blue@example.com'),
('laura_king', 'password123', 'TECHNICIAN', 'Laura', 'King', '1234567899', '1989-05-15', '2016-11-05', 'laura.king@example.com'),
('adam_wright', 'password123', 'MANAGER', 'Adam', 'Wright', '1234567890', '1975-07-20', '2005-10-30', 'adam.wright@example.com'),
('ella_fox', 'password123', 'INTERN', 'Ella', 'Fox', '1234567891', '1999-08-08', '2021-12-01', 'ella.fox@example.com'),
('jack_taylor', 'password123', 'ENGINEER', 'Jack', 'Taylor', '1234567892', '1991-03-14', '2019-05-15', 'jack.taylor@example.com'),
('olivia_hill', 'password123', 'TECHNICIAN', 'Olivia', 'Hill', '1234567893', '1988-02-19', '2014-09-22', 'olivia.hill@example.com'),
('mike_miller', 'password123', 'MANAGER', 'Mike', 'Miller', '1234567894', '1978-04-10', '2006-12-15', 'mike.miller@example.com'),
('anna_wood', 'password123', 'ENGINEER', 'Anna', 'Wood', '1234567895', '1986-07-25', '2012-01-20', 'anna.wood@example.com'),
('tom_moore', 'password123', 'TECHNICIAN', 'Tom', 'Moore', '1234567896', '1994-11-05', '2017-08-14', 'tom.moore@example.com'),
('sophia_adams', 'password123', 'INTERN', 'Sophia', 'Adams', '1234567897', '2001-05-30', '2023-03-10', 'sophia.adams@example.com'),
('ben_jones', 'password123', 'MANAGER', 'Ben', 'Jones', '1234567898', '1982-10-20', '2007-07-05', 'ben.jones@example.com'),
('mia_davis', 'password123', 'ENGINEER', 'Mia', 'Davis', '1234567899', '1990-12-12', '2016-02-28', 'mia.davis@example.com');