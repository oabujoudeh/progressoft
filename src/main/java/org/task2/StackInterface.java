package org.task2;

// Generified from a String-only stack to StackInterface<T> so callers (e.g. task4's
// EquationProcessor) can store real typed values (Double) instead of parsing to/from String.
public interface StackInterface<T> {

    void push(T item);

    T pop();

    T peek();

    int size();

    boolean isEmpty();
}
