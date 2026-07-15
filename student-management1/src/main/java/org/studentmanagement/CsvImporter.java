package org.studentmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    public static List<Student> readStudents(String filePath) {

        List<Student> students = new ArrayList<>();

        try (
                BufferedReader reader =
                        new BufferedReader(new FileReader(filePath))
        ) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                String[] values = line.split(",", -1);

                if (values.length != 8) {
                    return null;
                }

                try {
                    String fullName = values[0].trim();
                    String grade = values[1].trim();
                    String classSection = values[2].trim();

                    LocalDate dateOfBirth =
                            LocalDate.parse(values[3].trim());

                    String phoneNumber = values[4].trim();
                    String email = values[5].trim();
                    String address = values[6].trim();
                    String nationality = values[7].trim();

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

                    if (!StudentValidator.isValidStudent(student)) {
                        return null;
                    }

                    students.add(student);

                } catch (DateTimeParseException e) {
                    return null;
                }
            }

        } catch (IOException e) {
            return null;
        }

        return students;
    }
}