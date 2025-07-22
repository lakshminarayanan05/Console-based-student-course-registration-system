# Student Course Registration System

A simple Java-based application to manage students, courses, and their registrations using JDBC and MySQL.

---

## 📌 Features

- Add/Delete Students
- Add/Delete Courses
- Register Students to Courses
- View all Students/Courses
- View Courses by Student
- View Students by Course
- Unregister Student from Course
- View all Registrations

---

## 🛠️ Tech Stack

- **Java** (Core)
- **JDBC** (Java Database Connectivity)
- **MySQL** (Relational Database)
- **IntelliJ IDEA** (Recommended IDE)

---

## 📁 Project Structure

StudentCourseRegistration/

├── src/

│ ├── dao/

│ │ ├── StudentDAO.java

│ │ ├── CourseDAO.java

│ │ └── RegistrationDAO.java

│ ├── model/

│ │ ├── Student.java

│ │ └── Course.java

│ ├── ui/

│ │ └── Main.java

│ └── db/

│ └── db.properties

└── lib/

└── mysql-connector.jar

## 💾 Create the Database

```
CREATE DATABASE student_course_db;

USE student_course_db;

CREATE TABLE students (
    s_id INT AUTO_INCREMENT PRIMARY KEY,
    s_name VARCHAR(100),
    s_email VARCHAR(100)
);

CREATE TABLE courses (
    c_id INT AUTO_INCREMENT PRIMARY KEY,
    c_name VARCHAR(100),
    c_code VARCHAR(50)
);

CREATE TABLE registrations (
    r_id INT AUTO_INCREMENT PRIMARY KEY,
    s_id INT,
    c_id INT,
    register_date DATE,
    FOREIGN KEY (s_id) REFERENCES students(s_id),
    FOREIGN KEY (c_id) REFERENCES courses(c_id),
    UNIQUE (s_id, c_id)
);
```

## ✍️ Author
### Lakshmi Narayanan N
