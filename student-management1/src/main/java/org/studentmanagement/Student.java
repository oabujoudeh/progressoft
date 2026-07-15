package org.studentmanagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "grade", nullable = false, length = 20)
    private String grade;

    @Column(name = "class_section", nullable = false, length = 1)
    private String classSection;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(
            name = "phone_number",
            nullable = false,
            unique = true,
            length = 20
    )
    private String phoneNumber;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            length = 100
    )
    private String email;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "nationality", nullable = false, length = 20)
    private String nationality;

    /*
     * JPA requires a no-argument constructor.
     * Hibernate uses it when loading students from the database.
     */
    protected Student() {
    }

    public Student(
            String fullName,
            String grade,
            String classSection,
            LocalDate dateOfBirth,
            String phoneNumber,
            String email,
            String address,
            String nationality
    ) {
        this.id = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.grade = grade;
        this.classSection = classSection;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(
                dateOfBirth,
                LocalDate.now()
        ).getYears();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGrade() {
        return grade;
    }

    public String getClassSection() {
        return classSection;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClassSection(String classSection) {
        this.classSection = classSection;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return """
                ------------------------------
                ID: %s
                Full Name: %s
                Grade: %s
                Class: %s
                Date of Birth: %s
                Age: %d
                Phone Number: %s
                Email: %s
                Address: %s
                Nationality: %s
                ------------------------------
                """.formatted(
                id,
                fullName,
                grade,
                classSection,
                dateOfBirth,
                age,
                phoneNumber,
                email,
                address,
                nationality
        );
    }
}