# SmartStudent – Student Management System

<img width="1872" height="963" alt="Screenshot 2026-09-19 160127" src="https://github.com/user-attachments/assets/3eb10907-a46f-45b6-8c1b-938632963317" />
<img width="1611" height="636" alt="Screenshot 2026-09-19 140448" src="https://github.com/user-attachments/assets/f8571817-5990-41ac-8ae1-64640ae4e20a" />
<img width="1797" height="923" alt="Screenshot 2026-09-19 133808" src="https://github.com/user-attachments/assets/5b8620ba-874b-4e70-8ae8-efd5e51813e3" />
<img width="1867" height="931" alt="Screenshot 2026-09-18 205557" src="https://github.com/user-attachments/assets/0f7efb5b-d2f0-423e-8fa9-1fffffebf5ea" />
<img width="1919" height="939" alt="Screenshot 2026-09-18 205541" src="https://github.com/user-attachments/assets/522a2d4b-9aaa-4c5e-ac60-9fa9aee18cc3" />



## Project Overview

SmartStudent is a Java-based Student Management System developed using Java, JDBC, and PostgreSQL.

The application allows an administrator to manage student records through a simple console-based interface.

## Features

* Admin login
* Add student
* View all students
* Search student by roll number
* Search student by name
* Search student by department
* Search students based on marks
* Update student information
* Delete student records
* Student statistics
* PostgreSQL database integration
* JDBC-based database operations

## Technologies Used

* Java
* JDBC
* PostgreSQL
* Maven
* Git
* GitHub

## Database

Database name:

`smartstudent`

Table:

`students`

Fields:

* id
* name
* roll_no
* department
* email
* phone
* marks

## Admin Login

Username:

`admin`

Password:

`admin123`

## How to Run

### 1. Create Database

Create a SQL database named:

`smartstudent`

### 2. Create Table

Run the SQL commands available in:

`student.sql`

### 3. Configure Database

Open:

`DatabaseConnection.java`

Update the PostgreSQL username and password.

### 4. Install Dependencies

If using Maven, run:

```bash
mvn clean install
```

### 5. Run Application

Run:

`Main.java`

## Project Architecture

```text
Main
  |
  v
UI
  |
  +---- AdminService
  |
  v
StudentDAO
  |
  v
DatabaseConnection
  |
  v
SQL


## CRUD Operations

### Create

Adds a new student to the database.

### Read

Displays all students or searches for specific students.

### Update

Updates existing student information.

### Delete

Deletes a student using the roll number.

## Future Improvements

* Java Swing GUI
* Database-based admin authentication
* Password hashing
* Subject-wise marks
* Automatic grade calculation
* CSV export
* Pagination
* Student login
* Admin dashboard
* Validation and exception handling improvements

## Author

Purusothaman
