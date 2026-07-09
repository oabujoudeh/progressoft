package org.task6;

public class SimpleStatistic implements Statistic{
    private String key;
    private Object value;

    public SimpleStatistic (String key, Object value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
