package org.task6;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class EmployeeStatisticsCollector implements StatisticsCollector<Employee, Statistic> {

    // Each classifier maps one employee to the statistic label it belongs to; returning null
    // skips that classifier for that employee (e.g. resignation year for current employees).
    private static final List<Function<Employee, String>> CLASSIFIERS = Arrays.asList(
            employee -> "Birth year " + employee.getBirthDate().getYear(),
            employee -> "Birth in " + employee.getBirthPlace(),
            employee -> "Position " + employee.getPosition(),
            employee -> employee.getResignationDate() != null
                    ? "Resigned in " + employee.getResignationDate().getYear()
                    : null,
            EmployeeStatisticsCollector::salaryRangeLabel
    );

    // was "Employee Statsitics Collector" (typo) and the old "Birth year" + year / "Position" +
    // position concatenations were missing a space; both are fixed here since labels are built
    // in one place now instead of 4 separate string-concatenation sites.
    private final GroupingStatisticsCollector<Employee> delegate =
            new GroupingStatisticsCollector<>("Employee Statistics Collector", CLASSIFIERS);

    private static String salaryRangeLabel(Employee employee) {
        double salary = employee.getSalary();
        if (salary < 350) {
            return "Salary < 350";
        } else if (salary < 600) {
            return "350 <= Salary < 600";
        } else if (salary <= 1200) {
            return "600 <= Salary <= 1200";
        } else {
            return "Salary > 1200";
        }
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Iterable<Statistic> collectStatistics(Iterable<Employee> objects) {
        return delegate.collectStatistics(objects);
    }
}
