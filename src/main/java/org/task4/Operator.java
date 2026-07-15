package org.task4;

// Replaces the old if/else-if chains in priority()/calculateTop(): each operator now knows
// how to apply and prioritize itself, instead of EquationProcessor branching on its symbol.
public interface Operator {

    double apply(double left, double right);

    int priority();
}
