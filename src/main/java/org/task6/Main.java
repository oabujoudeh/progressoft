package org.task6;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                "Omar",
                "Ali",
                LocalDate.of(1999, 5, 10),
                "Jordan",
                LocalDate.of(2022, 1, 1),
                null,
                "Developer",
                500
        ));

        employees.add(new Employee(
                "Sara",
                "Ahmad",
                LocalDate.of(1999, 3, 20),
                "Jordan",
                LocalDate.of(2021, 6, 15),
                LocalDate.of(2024, 2, 1),
                "Designer",
                1300
        ));

        employees.add(new Employee(
                "Khaled",
                "Hassan",
                LocalDate.of(2000, 8, 12),
                "Kuwait",
                LocalDate.of(2023, 4, 10),
                null,
                "Developer",
                300
        ));

        employees.add(new Employee(
                "Lina",
                "Saleh",
                LocalDate.of(2001, 11, 5),
                "Jordan",
                LocalDate.of(2020, 9, 1),
                LocalDate.of(2023, 12, 20),
                "Manager",
                900
        ));

        StatisticsCollector<Employee, Statistic> collector =
                new EmployeeStatisticsCollector();

        Iterable<Statistic> statistics =
                collector.collectStatistics(employees);

        System.out.println("Employees Statistics:");
        System.out.printf("%-30s | %-10s%n", "Statistic Name", "Value");
        System.out.println("********************************************");

        for (Statistic statistic : statistics) {
            System.out.printf("%-30s | %-10s%n",
                    statistic.getKey(),
                    statistic.getValue());
        }
    }
}