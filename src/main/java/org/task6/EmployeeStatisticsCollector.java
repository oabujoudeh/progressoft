package org.task6;

import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeStatisticsCollector implements StatisticsCollector<Employee, Statistic> {
    @Override
    public String getName(){
        return "Employee Statsitics Collector";
    }

    @Override
    public Iterable<Statistic> collectStatistics(Iterable<Employee> objects){
        ArrayList<Statistic> statistics = new ArrayList<>();

        HashMap<String, Integer> birthYears = new HashMap<>();
        HashMap<String, Integer> birthPlaces = new HashMap<>();
        HashMap<String, Integer> positions = new HashMap<>();
        HashMap<String, Integer> resignationYears = new HashMap<>();

        int below350 = 0;
        int between350And600 = 0;
        int between600And1200 = 0;
        int above1200 = 0;

        for (Employee employee : objects){
            String year = String.valueOf(employee.getBirthDate().getYear());

            if (birthYears.containsKey(year)){
                birthYears.put(year, birthYears.get(year)+1);
            } else{
                birthYears.put(year, 1);
            }

            String birthPlace = employee.getBirthPlace();

            if (birthPlaces.containsKey(birthPlace)) {
                birthPlaces.put(birthPlace, birthPlaces.get(birthPlace) + 1);
            } else {
                birthPlaces.put(birthPlace, 1);
            }

            String position = employee.getPosition();

            if (positions.containsKey(position)) {
                positions.put(position, positions.get(position)+1);
            } else {
                positions.put(position, 1);
            }

            if (employee.getResignationDate() != null) {

                String resignationYear = String.valueOf(employee.getResignationDate().getYear());

                if (resignationYears.containsKey(resignationYear)) {
                    resignationYears.put(resignationYear, resignationYears.get(resignationYear) + 1);
                } else {
                    resignationYears.put(resignationYear, 1);
                }
            }

            double salary = employee.getSalary();
            if (salary < 350) {
                below350++;
            }
            else if (salary >= 350 && salary < 600) {
                between350And600++;
            }
            else if (salary >= 600 && salary <= 1200) {
                between600And1200++;
            }
            else {
                above1200++;
            }


        }
        for (String year : birthYears.keySet()){
            statistics.add(new SimpleStatistic("Birth year" + year, birthYears.get(year)));
        }
        for (String birthPlace : birthPlaces.keySet()) {
            statistics.add(new SimpleStatistic("Birth in " + birthPlace, birthPlaces.get(birthPlace)));
        }
        for ( String position : positions.keySet()){
            statistics.add(new SimpleStatistic("Position" + position, positions.get(position)));
        }
        for (String resignationYear : resignationYears.keySet()) {
            statistics.add(new SimpleStatistic("Resigned in " + resignationYear, resignationYears.get(resignationYear)));
        }
        statistics.add(new SimpleStatistic("Salary < 350", below350));

        statistics.add(new SimpleStatistic("350 <= Salary < 600", between350And600));

        statistics.add(new SimpleStatistic("600 <= Salary <= 1200", between600And1200));

        statistics.add(new SimpleStatistic("Salary > 1200", above1200));

        return statistics;
    }
}
