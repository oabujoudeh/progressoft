package org.task2;

public class FixedQueue implements QueueInterface{

    private String[] data;
    private int front;
    private int back;
    private int size;
    private int capacity;

    public FixedQueue(int capacity){
        this.capacity = capacity;
        this.data = new String[capacity];
        this.front = -1;
        this.back = -1;
        this.size = 0;
    }


    @Override
    public void enqueue(String item) {

        if (size == capacity) {
            throw new RuntimeException("Queue is full");
        }

        if (front == -1) {
            front = 0;
        }

        back++;

        data[back] = item;

        size++;
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

    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String peek(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return data[front];
    }
}