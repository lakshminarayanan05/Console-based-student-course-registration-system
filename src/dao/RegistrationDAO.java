package dao;

import model.Course;
import model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    private final Connection conn;

    public RegistrationDAO() {
        this(DBConnection.getDBConnection());
    }

    public RegistrationDAO(Connection conn) {
        this.conn = conn;
    }
    public void registerStudentToCourse(int studentId, int courseId, LocalDate registerDate) {
        String query = "INSERT INTO registrations (s_id, c_id, register_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDate(3, Date.valueOf(registerDate));
            int row = ps.executeUpdate();
            System.out.println(row + " row(s) affected. Student registered.");
        } catch (SQLException e) {
            System.out.println("Registration failed (maybe already registered?)");
            e.printStackTrace();
        }
    }
    public List<Course> getCoursesByStudentId(int studentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.* FROM courses c JOIN registrations r ON c.c_id = r.c_id WHERE r.s_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getInt("c_id"), rs.getString("c_name"), rs.getString("c_code"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public List<Student> getStudentsByCourseId(int courseId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT s.* FROM students s JOIN registrations r ON s.s_id = r.s_id WHERE r.c_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("s_id"), rs.getString("name"), rs.getString("email"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public void unregisterStudentFromCourse(int studentId, int courseId) {
        String query = "DELETE FROM registrations WHERE s_id = ? AND c_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getAllRegistrations() {
        List<String> list = new ArrayList<>();
        String query = "SELECT r.r_id, s.name AS student, c.c_name AS course, r.register_date " +
                "FROM registrations r " +
                "JOIN students s ON r.s_id = s.s_id " +
                "JOIN courses c ON r.c_id = c.c_id";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add("RID: " + rs.getInt("r_id") + ", Student: " + rs.getString("student") +
                        ", Course: " + rs.getString("course") + ", Date: " + rs.getDate("register_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}