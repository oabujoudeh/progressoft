package org.task6;

import java.time.LocalDate;

public class Employee {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String birthPlace;
    private LocalDate hiringDate;
    private LocalDate resignationDate;
    private String position;
    private double salary;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public LocalDate getResignationDate() {
        return resignationDate;
    }

    public void setResignationDate(LocalDate resignationDate) {
        this.resignationDate = resignationDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, LocalDate birthDate,
                    String birthPlace, LocalDate hiringDate,
                    LocalDate resignationDate, String position, double salary) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.hiringDate = hiringDate;
        this.resignationDate = resignationDate;
        this.position = position;
        this.salary = salary;




    }

}