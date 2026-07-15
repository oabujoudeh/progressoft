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
            // single pass per string instead of 3 separate scans (char loop + "\\s+" split +
            // "\\D+" split); word/number boundaries are tracked with two small state flags.
            boolean inWord = false;
            boolean inNumber = false;

            for (int i = 0 ; i < object.length(); i++){
                char current = object.charAt(i);

                if (Character.isUpperCase(current)){
                    upperCaseCount++;
                }
                if (Character.isLowerCase(current)){
                    lowerCaseCount++;
                }
                if (!Character.isLetterOrDigit(current) && !Character.isWhitespace(current)){
                    nonWordCount++;
                }

                boolean whitespace = Character.isWhitespace(current);
                if (!whitespace && !inWord) {
                    wordCount++;
                }
                inWord = !whitespace;

                boolean digit = Character.isDigit(current);
                if (digit && !inNumber) {
                    numberCount++;
                }
                inNumber = digit;
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
