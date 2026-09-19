import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ADD STUDENT
    public boolean addStudent(Student student) {

        String sql = """
                INSERT INTO students
                (name, roll_no, department, email, phone, marks)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getRollNo());
            statement.setString(3, student.getDepartment());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setDouble(6, student.getMarks());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }


    // VIEW ALL STUDENTS
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Student student = new Student();

                student.setId(result.getInt("id"));
                student.setName(result.getString("name"));
                student.setRollNo(result.getString("roll_no"));
                student.setDepartment(result.getString("department"));
                student.setEmail(result.getString("email"));
                student.setPhone(result.getString("phone"));
                student.setMarks(result.getDouble("marks"));

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return students;
    }


    // SEARCH BY ROLL NUMBER
    public Student getStudentByRollNo(String rollNo) {

        String sql =
                "SELECT * FROM students WHERE roll_no = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, rollNo);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                return new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("roll_no"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return null;
    }


    // SEARCH BY NAME
    public List<Student> searchByName(String name) {

        List<Student> students = new ArrayList<>();

        String sql =
                "SELECT * FROM students WHERE name ILIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                students.add(new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("roll_no"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getDouble("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return students;
    }


    // SEARCH BY DEPARTMENT
    public List<Student> searchByDepartment(String department) {

        List<Student> students = new ArrayList<>();

        String sql =
                "SELECT * FROM students WHERE department ILIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, "%" + department + "%");

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                students.add(new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("roll_no"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getDouble("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return students;
    }


    // SEARCH STUDENTS ABOVE MARKS
    public List<Student> searchByMarks(double marks) {

        List<Student> students = new ArrayList<>();

        String sql =
                "SELECT * FROM students WHERE marks > ? ORDER BY marks DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, marks);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                students.add(new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("roll_no"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getDouble("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return students;
    }


    // UPDATE STUDENT
    public boolean updateStudent(Student student) {

        String sql = """
                UPDATE students
                SET name = ?,
                    department = ?,
                    email = ?,
                    phone = ?,
                    marks = ?
                WHERE roll_no = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getDepartment());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setDouble(5, student.getMarks());
            statement.setString(6, student.getRollNo());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }


    // DELETE STUDENT
    public boolean deleteStudent(String rollNo) {

        String sql =
                "DELETE FROM students WHERE roll_no = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, rollNo);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }


    // TOTAL STUDENTS
    public int getTotalStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return 0;
    }


    // HIGHEST MARK
    public double getHighestMarks() {

        String sql = "SELECT COALESCE(MAX(marks), 0) FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return result.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return 0;
    }


    // LOWEST MARK
    public double getLowestMarks() {

        String sql = "SELECT COALESCE(MIN(marks), 0) FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return result.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return 0;
    }
}
