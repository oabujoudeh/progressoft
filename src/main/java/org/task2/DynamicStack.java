package org.task2;

public class DynamicStack implements StackInterface {

    private String[] data;
    private int top;
    private int capacity;

    public DynamicStack() {
        this.capacity = 5;
        this.top = -1;
        this.data = new String[capacity];
    }

    @Override
    public void push(String item) {
        if (top == capacity - 1) {
            resize();
        }

        top++;
        data[top] = item;
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
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        String temp = data[top];
        top--;
        return temp;
    }

    @Override
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return data[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}