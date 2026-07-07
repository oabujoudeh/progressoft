package org.task2;

public class DynamicQueue implements QueueInterface {

    private String[] data;
    private int front;
    private int back;
    private int size;
    private int capacity;

    public DynamicQueue() {
        this.capacity = 5;
        this.data = new String[capacity];
        this.front = -1;
        this.back = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(String item) {
        if (size == capacity) {
            resize();
        }

        if (front == -1) {
            front = 0;
        }

        back++;
        data[back] = item;
        size++;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        String[] newData = new String[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
        capacity = newCapacity;
    }

    @Override
    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        String temp = data[front];

        front++;
        size--;

        if (size == 0) {
            front = -1;
            back = -1;
        }

        return temp;
    }

    @Override
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        return data[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}