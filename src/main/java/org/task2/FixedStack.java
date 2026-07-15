package org.task2;

// Generified alongside StackInterface/DynamicStack so both stack implementations
// stay interchangeable through the same typed interface.
public class FixedStack<T> implements StackInterface<T> {

    private T[] data;
    private int top;
    private int capacity;

    @SuppressWarnings("unchecked")
    public FixedStack(int capacity) {
        this.capacity = capacity;
        this.top = -1;
        this.data = (T[]) new Object[capacity];
    }

    @Override
    public void push(T item) {
        if (top == capacity - 1) {
            throw new RuntimeException("Stack is full");
        }
        top++;
        data[top] = item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        T temp = data[top];
        top--;
        return temp;
    }

    @Override
    public T peek() {
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
