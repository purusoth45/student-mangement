import java.util.List;
import java.util.Scanner;

public class UI {

    private final Scanner scanner;
    private final StudentDAO studentDAO;

    public UI() {
        scanner = new Scanner(System.in);
        studentDAO = new StudentDAO();
    }


    public void start() {

        AdminService adminService = new AdminService();

        System.out.println();
        System.out.println("======================================");
        System.out.println("       SMART STUDENT MANAGEMENT");
        System.out.println("======================================");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!adminService.login(username, password)) {

            System.out.println();
            System.out.println("Invalid username or password.");
            System.out.println("Access denied.");

            return;
        }

        System.out.println();
        System.out.println("Login successful!");

        showMenu();
    }


    private void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("              MAIN MENU");
            System.out.println("======================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Statistics");
            System.out.println("7. Exit");

            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addStudent();
                    break;

                case "2":
                    viewAllStudents();
                    break;

                case "3":
                    searchStudent();
                    break;

                case "4":
                    updateStudent();
                    break;

                case "5":
                    deleteStudent();
                    break;

                case "6":
                    showStatistics();
                    break;

                case "7":
                    System.out.println("Thank you for using SmartStudent.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ADD STUDENT
    private void addStudent() {

        System.out.println();
        System.out.println("========== ADD STUDENT ==========");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Roll No: ");
        String rollNo = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        double marks = readMarks();

        Student student = new Student(
                name,
                rollNo,
                department,
                email,
                phone,
                marks
        );

        if (studentDAO.addStudent(student)) {

            System.out.println();
            System.out.println("Student added successfully.");

        } else {

            System.out.println();
            System.out.println("Failed to add student.");
        }
    }


    // VIEW ALL STUDENTS
    private void viewAllStudents() {

        System.out.println();
        System.out.println("========== ALL STUDENTS ==========");

        List<Student> students = studentDAO.getAllStudents();

        displayStudents(students);
    }


    // SEARCH
    private void searchStudent() {

        while (true) {

            System.out.println();
            System.out.println("========== SEARCH STUDENT ==========");

            System.out.println("1. Search by Roll No");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Department");
            System.out.println("4. Search by Marks");
            System.out.println("5. Back");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    searchByRollNo();
                    break;

                case "2":
                    searchByName();
                    break;

                case "3":
                    searchByDepartment();
                    break;

                case "4":
                    searchByMarks();
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private void searchByRollNo() {

        System.out.print("Enter Roll No: ");

        String rollNo = scanner.nextLine();

        Student student =
                studentDAO.getStudentByRollNo(rollNo);

        if (student == null) {

            System.out.println("Student not found.");

        } else {

            displayStudent(student);
        }
    }


    private void searchByName() {

        System.out.print("Enter name: ");

        String name = scanner.nextLine();

        List<Student> students =
                studentDAO.searchByName(name);

        displayStudents(students);
    }


    private void searchByDepartment() {

        System.out.print("Enter department: ");

        String department = scanner.nextLine();

        List<Student> students =
                studentDAO.searchByDepartment(department);

        displayStudents(students);
    }


    private void searchByMarks() {

        double marks = readMarks();

        List<Student> students =
                studentDAO.searchByMarks(marks);

        displayStudents(students);
    }


    // UPDATE STUDENT
    private void updateStudent() {

        System.out.println();
        System.out.println("========== UPDATE STUDENT ==========");

        System.out.print("Enter Roll No: ");

        String rollNo = scanner.nextLine();

        Student existing =
                studentDAO.getStudentByRollNo(rollNo);

        if (existing == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println();
        System.out.println("Enter new information.");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        double marks = readMarks();

        Student student = new Student(
                name,
                rollNo,
                department,
                email,
                phone,
                marks
        );

        if (studentDAO.updateStudent(student)) {

            System.out.println("Student updated successfully.");

        } else {

            System.out.println("Update failed.");
        }
    }


    // DELETE STUDENT
    private void deleteStudent() {

        System.out.println();
        System.out.println("========== DELETE STUDENT ==========");

        System.out.print("Enter Roll No: ");

        String rollNo = scanner.nextLine();

        Student student =
                studentDAO.getStudentByRollNo(rollNo);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println(
                "Student: " + student.getName()
        );

        System.out.print("Are you sure? (yes/no): ");

        String confirmation =
                scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            if (studentDAO.deleteStudent(rollNo)) {

                System.out.println(
                        "Student deleted successfully."
                );

            } else {

                System.out.println("Delete failed.");
            }

        } else {

            System.out.println("Delete cancelled.");
        }
    }


    // STATISTICS
    private void showStatistics() {

        System.out.println();
        System.out.println("========== STATISTICS ==========");

        int total =
                studentDAO.getTotalStudents();

        double highest =
                studentDAO.getHighestMarks();

        double lowest =
                studentDAO.getLowestMarks();

        System.out.println(
                "Total Students : " + total
        );

        System.out.println(
                "Highest Marks  : " + highest
        );

        System.out.println(
                "Lowest Marks   : " + lowest
        );
    }


    // DISPLAY STUDENTS
    private void displayStudents(List<Student> students) {

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println();

        System.out.printf(
                "%-5s %-20s %-12s %-20s %-25s %-15s %-8s%n",
                "ID",
                "NAME",
                "ROLL NO",
                "DEPARTMENT",
                "EMAIL",
                "PHONE",
                "MARKS"
        );

        System.out.println(
                "--------------------------------------------------------------------------------------------------------------"
        );

        for (Student student : students) {

            System.out.printf(
                    "%-5d %-20s %-12s %-20s %-25s %-15s %-8.2f%n",
                    student.getId(),
                    student.getName(),
                    student.getRollNo(),
                    student.getDepartment(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getMarks()
            );
        }
    }


    // DISPLAY ONE STUDENT
    private void displayStudent(Student student) {

        System.out.println();
        System.out.println("Student Details");
        System.out.println("----------------------------");

        System.out.println(
                "ID         : " + student.getId()
        );

        System.out.println(
                "Name       : " + student.getName()
        );

        System.out.println(
                "Roll No    : " + student.getRollNo()
        );

        System.out.println(
                "Department : " + student.getDepartment()
        );

        System.out.println(
                "Email      : " + student.getEmail()
        );

        System.out.println(
                "Phone      : " + student.getPhone()
        );

        System.out.println(
                "Marks      : " + student.getMarks()
        );
    }


    // READ MARKS
    private double readMarks() {

        while (true) {

            try {

                System.out.print("Marks (0-100): ");

                double marks =
                        Double.parseDouble(scanner.nextLine());

                if (marks >= 0 && marks <= 100) {

                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}
