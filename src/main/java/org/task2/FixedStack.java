package org.task2;

public class FixedStack implements StackInterface {

    private String[] data;
    private int top;
    private int capacity;

    public FixedStack(int capacity) {
        this.capacity = capacity;
        this.top = -1;
        this.data = new String[capacity];
    }

    @Override
    public void push(String item) {
        if (top == capacity - 1) {
            throw new RuntimeException("Stack is full");
        }
        top++;
        data[top] = item;
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