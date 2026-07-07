package org.task4;

import org.task2.DynamicStack;
import org.task2.StackInterface;

import java.util.Scanner;


public class EquationProcessor {

    private StackInterface operands;
    private StackInterface operators;
    private java.util.HashMap<String, String> variables;
    private Scanner scanner;

    public EquationProcessor() {
        operands = new DynamicStack();
        operators = new DynamicStack();
        variables = new java.util.HashMap<>();
        scanner = new Scanner(System.in);
    }

    public double process(String equation) {

        String currentNumber = "";

        for (int i = 0; i < equation.length(); i++) {

            char current = equation.charAt(i);

            if (Character.isDigit(current)) {
                currentNumber = currentNumber + current;
            }

            else if (current == '+' ||
                    current == '-' ||
                    current == '*' ||
                    current == '/' ||
                    current == '^') {


                if (!currentNumber.equals("")) {
                    operands.push(currentNumber);
                    currentNumber = "";
                }

                String operator = String.valueOf(current);
                while (!operators.isEmpty() &&
                        !operators.peek().equals("(") &&
                        priority(operator) <= priority(operators.peek())) {

                    calculateTop();
                }

                operators.push(operator);

            }
            else if (current == '(') {

                operators.push("(");

            }

            else if (current == ')') {

                if (!currentNumber.equals("")) {
                    operands.push(currentNumber);
                    currentNumber = "";
                }

                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    calculateTop();
                }

                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }

                operators.pop(); // remove "("
            }

            else if (Character.isLetter(current)) {

                String variableName = String.valueOf(current);

                if (!variables.containsKey(variableName)) {
                    System.out.print("Enter value for " + variableName + ": ");
                    String value = scanner.nextLine();
                    variables.put(variableName, value);
                }

                operands.push(variables.get(variableName));

            }
        }

        if (!currentNumber.equals("")) {
            operands.push(currentNumber);

        }

        while (!operators.isEmpty()) {

            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }

            calculateTop();
        }

        return Double.parseDouble(operands.pop());


    }

    private int priority(String operator){
        if (operator.equals("^")) {
            return 3;
        }
        if (operator.equals("*") || operator.equals("/")){
            return 2;
        }
        if (operator.equals("+") || operator.equals("-")){
            return 1;
        }
        return 0;
    }

    private void calculateTop(){
        if (operators.isEmpty()){
            throw new IllegalArgumentException("Invalid Equation: missing operator");
        }
        if (operands.size() <2){
            throw new IllegalArgumentException("Invalid equation: missing operand");
        }
        String operator = operators.pop();
        String rightString = operands.pop();
        String leftString = operands.pop();

        double right = Double.parseDouble(rightString);
        double left = Double.parseDouble(leftString);

        double result = 0;

        if (operator.equals("+")){
            result = left + right;
        } else if (operator.equals("-")){
            result = left - right;
        } else if (operator.equals("^")) {
            result = Math.pow(left,right);
        } else if (operator.equals("*")) {
            result = left * right;
        } else if (operator.equals("/")){
            if (right==0){
                throw new ArithmeticException("Invalid equation: cannot divide by zero");
            }
            result = left / right;
        }

        operands.push(String.valueOf(result));
    }

}