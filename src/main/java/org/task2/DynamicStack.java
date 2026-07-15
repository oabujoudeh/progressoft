package org.task2;

public class DynamicStack<T> implements StackInterface<T> {

    private T[] data;
    private int top;
    private int capacity;

    // Array of a generic type can't be created directly in Java, so it's built as
    // Object[] and cast; this is confined to the class internals and is type-safe from
    // the outside since push/pop only ever store/return T.
    @SuppressWarnings("unchecked")
    public DynamicStack() {
        this.capacity = 5;
        this.top = -1;
        this.data = (T[]) new Object[capacity];
    }

    @Override
    public void push(T item) {
        if (top == capacity - 1) {
            resize();
        }

        top++;
        data[top] = item;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newData = (T[]) new Object[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
        capacity = newCapacity;
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
