package org.task4;
import org.task2.DynamicStack;
import org.task2.StackInterface;
import java.util.Scanner;
public class EquationProcessor {

    // operands now hold Double directly instead of String, removing the constant
    // Double.parseDouble/String.valueOf round-tripping the old String-typed stack forced.
    // operators stays String-typed since it also carries the "(" sentinel, which isn't an Operator.
    private StackInterface<Double> operands;
    private StackInterface<String> operators;
    private java.util.HashMap<String, Double> variables;
    private Scanner scanner;

    // Scanner is now passed in instead of created here, so Main's Scanner and this one
    // share a single buffer over System.in (two Scanners on the same stream can silently
    // drop/duplicate input).
    public EquationProcessor(Scanner scanner) {
        operands = new DynamicStack<>();
        operators = new DynamicStack<>();
        variables = new java.util.HashMap<>();
        this.scanner = scanner;
    }

    public double process(String equation) {

        String currentNumber = "";

        for (int i = 0; i < equation.length(); i++) {

            char current = equation.charAt(i);

            // '.' accepted alongside digits so decimal literals (e.g. "3.5") parse correctly
            if (Character.isDigit(current) || current == '.') {
                currentNumber = currentNumber + current;
            }

            else if (ArithmeticOperator.isOperatorSymbol(current)) {


                if (!currentNumber.equals("")) {
                    operands.push(Double.parseDouble(currentNumber));
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
                    operands.push(Double.parseDouble(currentNumber));
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
                    double value = Double.parseDouble(scanner.nextLine());
                    variables.put(variableName, value);
                }

                operands.push(variables.get(variableName));

            }
        }

        if (!currentNumber.equals("")) {
            operands.push(Double.parseDouble(currentNumber));

        }

        while (!operators.isEmpty()) {

            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }

            calculateTop();
        }

        return operands.pop();


    }

    private int priority(String operator){
        // '(' is never passed here: the while-loop above short-circuits on
        // !operators.peek().equals("(") before priority(operators.peek()) is evaluated.
        return ArithmeticOperator.fromSymbol(operator).priority();
    }

    private void calculateTop(){
        if (operators.isEmpty()){
            throw new IllegalArgumentException("Invalid Equation: missing operator");
        }
        if (operands.size() <2){
            throw new IllegalArgumentException("Invalid equation: missing operand");
        }
        String operatorSymbol = operators.pop();
        double right = operands.pop();
        double left = operands.pop();

        // operator resolves and applies itself polymorphically; no more if/else-if chain
        double result = ArithmeticOperator.fromSymbol(operatorSymbol).apply(left, right);

        operands.push(result);
    }

}
