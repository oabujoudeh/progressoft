package org.task2;

public class Main {
    public static void main(String[] args) {

        // =====================
        // FIXED STACK
        // =====================
        // stacks are now generic; declared with <String> since that's what this demo pushes
        StackInterface<String> fixedStack = new FixedStack<>(5);

        fixedStack.push("A");
        fixedStack.push("B");
        fixedStack.push("C");

        System.out.println("Fixed Stack:");
        try {
            System.out.println(fixedStack.pop());
            System.out.println(fixedStack.pop());
            System.out.println(fixedStack.pop());
            System.out.println(fixedStack.pop());
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        fixedStack.push("A");
        fixedStack.push("B");
        fixedStack.push("C");

        System.out.println("Fixed Stack Size:");
        System.out.println(fixedStack.size()); // 3


        // =====================
        // DYNAMIC STACK
        // =====================
        StackInterface<String> dynamicStack = new DynamicStack<>();

        dynamicStack.push("A");
        dynamicStack.push("B");
        dynamicStack.push("C");
        dynamicStack.push("D");
        dynamicStack.push("E");
        dynamicStack.push("F");

        try {
            System.out.println(dynamicStack.pop());   // F
            System.out.println(dynamicStack.pop());   // E
            System.out.println(dynamicStack.pop());   // D
            System.out.println(dynamicStack.pop());   // C
            System.out.println(dynamicStack.pop());   // B
            System.out.println(dynamicStack.pop());   // A
            System.out.println(dynamicStack.pop());   // should throw exception
        }

        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        // =====================
        // FIXED QUEUE
        // =====================
        QueueInterface fixedQueue = new FixedQueue(3);

        try {
            fixedQueue.enqueue("A");
            fixedQueue.enqueue("B");
            fixedQueue.enqueue("C");
            fixedQueue.enqueue("D");   // should throw "Queue is full"
        }

        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Fixed Queue:");

        try {
            System.out.println(fixedQueue.peek());      // A
            System.out.println(fixedQueue.dequeue());   // A
            System.out.println(fixedQueue.dequeue());   // B
            System.out.println(fixedQueue.dequeue());   // C
            System.out.println(fixedQueue.dequeue());   // should throw empty
        }

        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(fixedQueue.size());


        // =====================
        // DYNAMIC QUEUE
        // =====================
        QueueInterface dynamicQueue = new DynamicQueue();

        dynamicQueue.enqueue("A");
        dynamicQueue.enqueue("B");
        dynamicQueue.enqueue("C");
        dynamicQueue.enqueue("D");
        dynamicQueue.enqueue("E");
        dynamicQueue.enqueue("F");   // triggers resize

        System.out.println("Dynamic Queue:");
        System.out.println(dynamicQueue.size());   // 6
        System.out.println(dynamicQueue.peek());   // A

        try {
            System.out.println(dynamicQueue.dequeue());   // A
            System.out.println(dynamicQueue.dequeue());   // B
            System.out.println(dynamicQueue.dequeue());   // C
            System.out.println(dynamicQueue.dequeue());   // D
            System.out.println(dynamicQueue.dequeue());   // E
            System.out.println(dynamicQueue.dequeue());   // F
            System.out.println(dynamicQueue.dequeue());   // should throw empty
        }

        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}