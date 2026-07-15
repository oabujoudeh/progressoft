package org.task4;

// Enum-with-per-constant-body is Java's idiomatic polymorphism for a small closed set of
// behaviors: each constant overrides apply() with its own arithmetic, so adding a new
// operator means adding a constant, not another else-if branch. ACCCEPTED, REJECTED status (2 values) enum
public enum ArithmeticOperator implements Operator {

    ADD("+", 1) {
        @Override
        public double apply(double left, double right) {
            return left + right;
        }
    },
    SUBTRACT("-", 1) {
        @Override
        public double apply(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY("*", 2) {
        @Override
        public double apply(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/", 2) {
        @Override
        public double apply(double left, double right) {
            if (right == 0) {
                throw new ArithmeticException("Invalid equation: cannot divide by zero");
            }
            return left / right;
        }
    },
    POWER("^", 3) {
        @Override
        public double apply(double left, double right) {
            return Math.pow(left, right);
        }
    };

    private final String symbol;
    private final int priority;

    ArithmeticOperator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    public static ArithmeticOperator fromSymbol(String symbol) {
        for (ArithmeticOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }

    public static boolean isOperatorSymbol(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}
