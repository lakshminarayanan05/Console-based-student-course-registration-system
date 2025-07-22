package dao;

import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private final Connection conn;

    public CourseDAO() {
        this(DBConnection.getDBConnection());
    }

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCourse(String name, String code) {
        String query = "INSERT INTO courses (c_name, c_code) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + "row(s) affected. Student Added Successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Course c = new Course();
                c.setId(resultSet.getInt("c_id"));
                c.setName(resultSet.getString("c_name"));
                c.setCode(resultSet.getString("c_code"));
                list.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Course getCourseById(int id) {
        String query = "SELECT * FROM courses WHERE c_id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Course(resultSet.getInt("c_id"), resultSet.getString("c_name"), resultSet.getString("c_code"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCourse(int id) {
        String query = "DELETE FROM courses WHERE c_id = ?";
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
