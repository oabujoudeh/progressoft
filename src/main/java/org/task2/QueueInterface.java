package org.task2;

public interface QueueInterface {

    void enqueue(String item);

    String dequeue();

    String peek();

    int size();

    boolean isEmpty();
}