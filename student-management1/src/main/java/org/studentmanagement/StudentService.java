package org.studentmanagement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public boolean addStudent(Student student) {

        if (!StudentValidator.isValidStudent(student)) {
            System.out.println("Invalid student data.");
            return false;
        }

        if (repository.existsByPhoneNumber(student.getPhoneNumber())) {
            System.out.println("Phone number already exists.");
            return false;
        }

        if (repository.existsByEmail(student.getEmail())) {
            System.out.println("Email already exists.");
            return false;
        }

        repository.save(student);
        return true;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(String id) {

        Optional<Student> student = repository.findById(id);

        return student.orElse(null);
    }

    public boolean deleteStudentById(String id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public boolean updateStudent(Student student) {

        if (student == null || !repository.existsById(student.getId())) {
            return false;
        }

        if (!StudentValidator.isValidStudent(student)) {
            System.out.println("Invalid student data.");
            return false;
        }

        if (repository.existsByPhoneNumberAndIdNot(
                student.getPhoneNumber(),
                student.getId()
        )) {
            System.out.println("Phone number already exists.");
            return false;
        }

        if (repository.existsByEmailAndIdNot(
                student.getEmail(),
                student.getId()
        )) {
            System.out.println("Email already exists.");
            return false;
        }

        repository.save(student);
        return true;
    }

    @Transactional
    public boolean addStudentsBatch(List<Student> students) {

        if (students == null || students.isEmpty()) {
            return false;
        }

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            if (!StudentValidator.isValidStudent(student)) {
                return false;
            }

            if (repository.existsByPhoneNumber(
                    student.getPhoneNumber()
            )) {
                return false;
            }

            if (repository.existsByEmail(student.getEmail())) {
                return false;
            }

            for (int j = i + 1; j < students.size(); j++) {

                Student otherStudent = students.get(j);

                if (student.getPhoneNumber().equals(
                        otherStudent.getPhoneNumber()
                )) {
                    return false;
                }

                if (student.getEmail().equals(
                        otherStudent.getEmail()
                )) {
                    return false;
                }
            }
        }

        repository.saveAll(students);
        return true;
    }
}