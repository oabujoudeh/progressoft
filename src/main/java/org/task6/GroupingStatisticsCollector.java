package org.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

// The lab tip nudges toward "notice the similarities between strategies, come up with a
// general implementation": EmployeeStatisticsCollector's birth-year/birth-place/position/
// resignation-year/salary-range stats were 4-5 copies of the same containsKey/put counting
// block. Here that counting logic is written once, and each statistic is just a classifier
// (Employee -> label) passed in - a Strategy-style use of polymorphism via Function.
public class GroupingStatisticsCollector<T> implements StatisticsCollector<T, Statistic> {

    private final String name;
    private final List<Function<T, String>> classifiers;

    public GroupingStatisticsCollector(String name, List<Function<T, String>> classifiers) {
        this.name = name;
        this.classifiers = classifiers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterable<Statistic> collectStatistics(Iterable<T> objects) {
        // TreeMap keeps output in a stable, sorted order (HashMap iteration order is arbitrary)
        Map<String, Integer> counts = new TreeMap<>();

        for (T object : objects) {
            for (Function<T, String> classifier : classifiers) {
                String key = classifier.apply(object);
                if (key != null) {
                    // one line replaces the old "containsKey ? put(+1) : put(1)" block
                    counts.merge(key, 1, Integer::sum);
                }
            }
        }

        List<Statistic> statistics = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            statistics.add(new SimpleStatistic(entry.getKey(), entry.getValue()));
        }
        return statistics;
    }
}
