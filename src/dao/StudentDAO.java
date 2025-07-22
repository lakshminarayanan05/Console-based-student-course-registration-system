package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection conn;

    public StudentDAO() {
        this(DBConnection.getDBConnection());
    }

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public void addStudent(String name, String email) {
        String query = "INSERT INTO students (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + "row(s) affected. Student Added Successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Student s = new Student();
                s.setId(resultSet.getInt("s_id"));
                s.setName(resultSet.getString("name"));
                s.setEmail(resultSet.getString("email"));
                students.add(s);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE s_id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Student(resultSet.getInt("s_id"), resultSet.getString("name"), resultSet.getString("email"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE s_id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + "rows affected. Deleted Successfully");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
