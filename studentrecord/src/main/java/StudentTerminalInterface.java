import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.srijan.HibernateUtil;
import com.srijan.model.Student;
import com.srijan.repository.StudentRepository;
import com.srijan.repository.StudentRepositoryImpl;
import com.srijan.service.StudentService;

public class StudentTerminalInterface {
    private final StudentService studentService;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public StudentTerminalInterface() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        StudentRepository repository = new StudentRepositoryImpl(sessionFactory);
        this.studentService = new StudentService(repository);
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=== Student Repository System ===");
        System.out.println("Welcome to the Student Management System!");
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        searchStudent();
                        break;
                    case "3":
                        listAllStudents();
                        break;
                    case "4":
                        listStudentsByMajor();
                        break;
                    case "5":
                        updateStudent();
                        break;
                    case "6":
                        deleteStudent();
                        break;
                    case "7":
                        showStatistics();
                        break;
                    case "8":
                        System.out.println("Thank you for using the Student Repository System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Student Repository System - Main Menu");
        System.out.println("=".repeat(50));
        System.out.println("1. Add New Student");
        System.out.println("2. Search Student");
        System.out.println("3. List All Students");
        System.out.println("4. List Students by Major");
        System.out.println("5. Update Student");
        System.out.println("6. Delete Student");
        System.out.println("7. Show Statistics");
        System.out.println("8. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-8): ");
    }
    
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine().trim();
        
        LocalDate dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.print("Date of Birth (yyyy-mm-dd): ");
            String dobStr = scanner.nextLine().trim();
            try {
                dateOfBirth = LocalDate.parse(dobStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd format.");
            }
        }
        
        System.out.print("Phone (optional): ");
        String phone = scanner.nextLine().trim();
        if (phone.isEmpty()) phone = null;
        
        System.out.print("Major: ");
        String major = scanner.nextLine().trim();
        
        try {
            studentService.addStudent(firstName, lastName, email, studentId, dateOfBirth, phone, major);
            System.out.println("✓ Student added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Failed to add student: " + e.getMessage());
        }
    }
    
    private void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.println("1. Search by Student ID");
        System.out.println("2. Search by Email");
        System.out.print("Choose search method (1-2): ");
        
        String choice = scanner.nextLine().trim();
        Optional<Student> student = Optional.empty();
        
        switch (choice) {
            case "1":
                System.out.print("Enter Student ID: ");
                String studentId = scanner.nextLine().trim();
                student = studentService.findStudentByStudentId(studentId);
                break;
            case "2":
                System.out.print("Enter Email: ");
                String email = scanner.nextLine().trim();
                student = studentService.findStudentByEmail(email);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        if (student.isPresent()) {
            System.out.println("\n✓ Student found:");
            displayStudentDetails(student.get());
        } else {
            System.out.println("✗ Student not found.");
        }
    }
    
    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
        } else {
            System.out.printf("Found %d student(s):\n\n", students.size());
            students.forEach(this::displayStudentSummary);
        }
    }
    
    private void listStudentsByMajor() {
        System.out.println("\n--- Students by Major ---");
        System.out.print("Enter Major: ");
        String major = scanner.nextLine().trim();
        
        List<Student> students = studentService.getStudentsByMajor(major);
        
        if (students.isEmpty()) {
            System.out.println("No students found for major: " + major);
        } else {
            System.out.printf("Found %d student(s) in %s:\n\n", students.size(), major);
            students.forEach(this::displayStudentSummary);
        }
    }
    
    private void updateStudent() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        String studentId = scanner.nextLine().trim();
        
        Optional<Student> optionalStudent = studentService.findStudentByStudentId(studentId);
        if (!optionalStudent.isPresent()) {
            System.out.println("✗ Student not found.");
            return;
        }
        
        Student student = optionalStudent.get();
        System.out.println("Current student details:");
        displayStudentDetails(student);
        
        System.out.println("\nEnter new values (press Enter to keep current value):");
        
        System.out.print("First Name [" + student.getFirstName() + "]: ");
        String firstName = scanner.nextLine().trim();
        if (!firstName.isEmpty()) student.setFirstName(firstName);
        
        System.out.print("Last Name [" + student.getLastName() + "]: ");
        String lastName = scanner.nextLine().trim();
        if (!lastName.isEmpty()) student.setLastName(lastName);
        
        System.out.print("Phone [" + (student.getPhone() != null ? student.getPhone() : "none") + "]: ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) student.setPhone(phone);
        
        System.out.print("Major [" + student.getMajor() + "]: ");
        String major = scanner.nextLine().trim();
        if (!major.isEmpty()) student.setMajor(major);
        
        try {
            studentService.updateStudent(student);
            System.out.println("✓ Student updated successfully!");
        } catch (Exception e) {
            System.out.println("✗ Failed to update student: " + e.getMessage());
        }
    }
    
    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine().trim();
        
        Optional<Student> student = studentService.findStudentByStudentId(studentId);
        if (!student.isPresent()) {
            System.out.println("✗ Student not found.");
            return;
        }
        
        System.out.println("Student to be deleted:");
        displayStudentDetails(student.get());
        
        System.out.print("Are you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if ("yes".equals(confirmation) || "y".equals(confirmation)) {
            try {
                studentService.deleteStudentByStudentId(studentId);
                System.out.println("✓ Student deleted successfully!");
            } catch (Exception e) {
                System.out.println("✗ Failed to delete student: " + e.getMessage());
            }
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }
    
    private void showStatistics() {
        System.out.println("\n--- System Statistics ---");
        long totalStudents = studentService.getStudentCount();
        System.out.println("Total Students: " + totalStudents);
        
        if (totalStudents > 0) {
            System.out.println("\nRecent Students:");
            List<Student> allStudents = studentService.getAllStudents();
            allStudents.stream()
                    .limit(5)
                    .forEach(this::displayStudentSummary);
        }
    }
    
    private void displayStudentDetails(Student student) {
        System.out.println("-".repeat(40));
        System.out.println("ID: " + student.getId());
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Date of Birth: " + student.getDateOfBirth());
        System.out.println("Phone: " + (student.getPhone() != null ? student.getPhone() : "N/A"));
        System.out.println("Major: " + student.getMajor());
        System.out.println("Enrollment Date: " + student.getEnrollmentDate());
        System.out.println("-".repeat(40));
    }
    
    private void displayStudentSummary(Student student) {
        System.out.printf("%-10s | %-20s | %-30s | %-15s%n",
                student.getStudentId(),
                student.getFirstName() + " " + student.getLastName(),
                student.getEmail(),
                student.getMajor());
    }
    
    public static void main(String[] args) {
        try {
            StudentTerminalInterface app = new StudentTerminalInterface();
            app.start();
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}