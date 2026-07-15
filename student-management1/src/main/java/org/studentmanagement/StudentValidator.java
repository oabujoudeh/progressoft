package org.studentmanagement;

import java.time.LocalDate;
import java.util.Set;
import java.util.regex.Pattern;

public class StudentValidator {

    private static final Set<String> VALID_GRADES = Set.of(
            "7th Grade",
            "8th Grade",
            "9th Grade",
            "10th Grade"
    );

    private static final Set<String> VALID_CLASSES = Set.of(
            "A",
            "B",
            "C"
    );

    private static final Set<String> VALID_NATIONALITIES = Set.of(
            "Jordanian",
            "Syrian",
            "Palestinian"
    );

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[A-Za-z\\s._-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+962-\\d{9}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean isValidStudent(Student student) {
        return isValidFullName(student.getFullName())
                && isValidGrade(student.getGrade())
                && isValidClassSection(student.getClassSection())
                && isValidDateOfBirth(student.getDateOfBirth())
                && isValidPhoneNumber(student.getPhoneNumber())
                && isValidEmail(student.getEmail())
                && isValidAddress(student.getAddress())
                && isValidNationality(student.getNationality());
    }

    public static boolean isValidFullName(String fullName) {
        return fullName != null
                && !fullName.isBlank()
                && fullName.length() <= 50
                && NAME_PATTERN.matcher(fullName).matches();
    }

    public static boolean isValidGrade(String grade) {
        return grade != null && VALID_GRADES.contains(grade);
    }

    public static boolean isValidClassSection(String classSection) {
        return classSection != null && VALID_CLASSES.contains(classSection);
    }

    public static boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        LocalDate minimumDate = LocalDate.of(2010, 1, 1);

        return dateOfBirth != null
                && !dateOfBirth.isBefore(minimumDate)
                && !dateOfBirth.isAfter(LocalDate.now());
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null
                && PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null
                && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.isBlank() || address.length() > 100) {
            return false;
        }

        String[] parts = address.trim().split("\\s+");
        return parts.length == 3;
    }

    public static boolean isValidNationality(String nationality) {
        return nationality != null
                && VALID_NATIONALITIES.contains(nationality);
    }
}