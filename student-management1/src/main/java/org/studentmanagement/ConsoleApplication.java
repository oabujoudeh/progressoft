package org.studentmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {

    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApplication(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {

        boolean running = true;

        while (running) {
            printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listAllStudents();
                case "2" -> getStudentById();
                case "3" -> addStudent();
                case "4" -> editStudent();
                case "5" -> deleteStudent();
                case "6" -> bulkImport();
                case "7" -> {
                    running = false;
                    System.out.println("Application closed.");
                }
                default -> System.out.println(
                        "Invalid option. Choose from 1 to 7."
                );
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("""
                
                ===== Student Management System =====
                1. List All Students
                2. Get Student by ID
                3. Add New Student
                4. Edit Student
                5. Remove Student by ID
                6. Bulk Import
                7. Exit
                Choose an option:
                """);
    }

    private void listAllStudents() {

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void getStudentById() {

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    private void addStudent() {

        try {
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine().trim();

            String grade = selectGrade();
            String classSection = selectClassSection();

            System.out.print("Enter date of birth (yyyy-MM-dd): ");
            LocalDate dateOfBirth =
                    LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter phone number (+962-xxxxxxxxx): ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print(
                    "Enter address using exactly three parts: "
            );
            String address = scanner.nextLine().trim();

            String nationality = selectNationality();

            Student student = new Student(
                    fullName,
                    grade,
                    classSection,
                    dateOfBirth,
                    phoneNumber,
                    email,
                    address,
                    nationality
            );

            boolean added = studentService.addStudent(student);

            if (added) {
                System.out.println("Student added successfully.");
                System.out.println(
                        "Generated ID: " + student.getId()
                );
            } else {
                System.out.println("Student was not added.");
            }

        } catch (DateTimeParseException e) {
            System.out.println(
                    "Invalid date. Use yyyy-MM-dd."
            );
        }
    }

    private void editStudent() {

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        try {
            System.out.print("Enter new full name: ");
            student.setFullName(scanner.nextLine().trim());

            student.setGrade(selectGrade());
            student.setClassSection(selectClassSection());

            System.out.print(
                    "Enter new date of birth (yyyy-MM-dd): "
            );

            student.setDateOfBirth(
                    LocalDate.parse(scanner.nextLine().trim())
            );

            System.out.print(
                    "Enter new phone number (+962-xxxxxxxxx): "
            );
            student.setPhoneNumber(scanner.nextLine().trim());

            System.out.print("Enter new email: ");
            student.setEmail(scanner.nextLine().trim());

            System.out.print(
                    "Enter new address using exactly three parts: "
            );
            student.setAddress(scanner.nextLine().trim());

            student.setNationality(selectNationality());

            boolean updated = studentService.updateStudent(student);

            if (updated) {
                System.out.println(
                        "Student updated successfully."
                );
            } else {
                System.out.println(
                        "Student was not updated."
                );
            }

        } catch (DateTimeParseException e) {
            System.out.println(
                    "Invalid date. Use yyyy-MM-dd."
            );
        }
    }

    private void deleteStudent() {

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        boolean deleted = studentService.deleteStudentById(id);

        if (deleted) {
            System.out.println(
                    "Student deleted successfully."
            );
        } else {
            System.out.println("Student not found.");
        }
    }

    private void bulkImport() {

        System.out.print("Enter CSV file path: ");
        String filePath = scanner.nextLine().trim();

        List<Student> students =
                CsvImporter.readStudents(filePath);

        if (students == null || students.isEmpty()) {
            System.out.println(
                    "File contains invalid records."
            );
            return;
        }

        boolean imported =
                studentService.addStudentsBatch(students);

        if (imported) {
            System.out.println(
                    "Students imported successfully."
            );
        } else {
            System.out.println(
                    "File contains invalid records."
            );
        }
    }

    private String selectGrade() {

        while (true) {
            System.out.println("""
                    Select grade:
                    1. 7th Grade
                    2. 8th Grade
                    3. 9th Grade
                    4. 10th Grade
                    """);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    return "7th Grade";
                case "2":
                    return "8th Grade";
                case "3":
                    return "9th Grade";
                case "4":
                    return "10th Grade";
                default:
                    System.out.println(
                            "Invalid grade option."
                    );
            }
        }
    }

    private String selectClassSection() {

        while (true) {
            System.out.println("""
                    Select class section:
                    1. A
                    2. B
                    3. C
                    """);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    return "A";
                case "2":
                    return "B";
                case "3":
                    return "C";
                default:
                    System.out.println(
                            "Invalid class option."
                    );
            }
        }
    }

    private String selectNationality() {

        while (true) {
            System.out.println("""
                    Select nationality:
                    1. Jordanian
                    2. Syrian
                    3. Palestinian
                    """);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    return "Jordanian";
                case "2":
                    return "Syrian";
                case "3":
                    return "Palestinian";
                default:
                    System.out.println(
                            "Invalid nationality option."
                    );
            }
        }
    }
}