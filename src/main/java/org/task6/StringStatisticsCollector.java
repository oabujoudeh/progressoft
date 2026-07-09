package org.task6;

import java.util.ArrayList;

public class StringStatisticsCollector implements StatisticsCollector<String, Statistic> {

    @Override
    public String getName() {
        return "String Statistics Collector";
    }

    @Override
    public Iterable<Statistic> collectStatistics(Iterable<String> objects) {
        ArrayList<Statistic> statistics = new ArrayList<>();

        int upperCaseCount = 0;
        int lowerCaseCount = 0;
        int nonWordCount = 0;
        int wordCount = 0;
        int numberCount = 0;



        for (String object : objects){
            if (!object.trim().isEmpty()) {

                String[] words = object.trim().split("\\s+");

                wordCount += words.length;

            }

            String[] numbers = object.split("\\D+");

            for (String number : numbers) {

                if (!number.isEmpty()) {
                    numberCount++;
                }

            }
            for (int i = 0 ; i < object.length(); i++){
                if (Character.isUpperCase(object.charAt(i))){
                    upperCaseCount ++;
                }
                if (Character.isLowerCase(object.charAt(i))){
                    lowerCaseCount ++;
                }
                char current = object.charAt(i);
                if (!Character.isLetterOrDigit(current) && ! Character.isWhitespace(current)){
                    nonWordCount++;
                }
            }
        }

        statistics.add(new SimpleStatistic("Uppercase letters", upperCaseCount));
        statistics.add(new SimpleStatistic("Lowercase letters", lowerCaseCount));
        statistics.add(new SimpleStatistic("Non words", nonWordCount));
        statistics.add(new SimpleStatistic("Words", wordCount));
        statistics.add(new SimpleStatistic("Numbers", numberCount));
        return statistics;

    }
}