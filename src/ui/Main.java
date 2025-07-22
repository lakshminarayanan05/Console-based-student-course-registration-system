package ui;

import dao.CourseDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import model.Course;
import model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        RegistrationDAO registrationDAO = new RegistrationDAO();

        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student to Course");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. View Courses by Student ID");
            System.out.println("7. View Students by Course ID");
            System.out.println("8. Unregister Student from Course");
            System.out.println("9. View All Registrations");
            System.out.println("10. Delete Student");
            System.out.println("11. Delete Course");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String sName = sc.nextLine();
                    System.out.print("Enter student email: ");
                    String sEmail = sc.nextLine();
                    studentDAO.addStudent(sName, sEmail);
                }
                case 2 -> {
                    System.out.print("Enter course name: ");
                    String cName = sc.nextLine();
                    System.out.print("Enter course code: ");
                    String cCode = sc.nextLine();
                    courseDAO.addCourse(cName, cCode);
                }
                case 3 -> {
                    System.out.print("Enter student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter course ID: ");
                    int cid = sc.nextInt();
                    registrationDAO.registerStudentToCourse(sid, cid, LocalDate.now());
                }
                case 4 -> {
                    List<Student> students = studentDAO.getAllStudents();
                    System.out.println("Students:");
                    for (Student s : students)
                        System.out.println(s);
                }
                case 5 -> {
                    List<Course> courses = courseDAO.getAllCourses();
                    System.out.println("Courses:");
                    for (Course c : courses)
                        System.out.println(c);
                }
                case 6 -> {
                    System.out.print("Enter student ID: ");
                    int sid2 = sc.nextInt();
                    List<Course> studentCourses = registrationDAO.getCoursesByStudentId(sid2);
                    System.out.println("Registered Courses:");
                    for (Course c : studentCourses)
                        System.out.println(c);
                }
                case 7 -> {
                    System.out.print("Enter course ID: ");
                    int cid2 = sc.nextInt();
                    List<Student> courseStudents = registrationDAO.getStudentsByCourseId(cid2);
                    System.out.println("Registered Students:");
                    for (Student s : courseStudents)
                        System.out.println(s);
                }
                case 8 -> {
                    System.out.print("Enter student ID: ");
                    int sid3 = sc.nextInt();
                    System.out.print("Enter course ID: ");
                    int cid3 = sc.nextInt();
                    registrationDAO.unregisterStudentFromCourse(sid3, cid3);
                }
                case 9 -> {
                    List<String> registrations = registrationDAO.getAllRegistrations();
                    System.out.println("Registrations:");
                    for (String r : registrations)
                        System.out.println(r);
                }
                case 10 -> {
                    System.out.print("Enter student ID: ");
                    int sid4 = sc.nextInt();
                    studentDAO.deleteStudent(sid4);
                }
                case 11 -> {
                    System.out.print("Enter course ID: ");
                    int cid4 = sc.nextInt();
                    courseDAO.deleteCourse(cid4);
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            sc.nextLine();
            System.out.println("Press enter to continue..."); sc.nextLine();
        }
    }
}
