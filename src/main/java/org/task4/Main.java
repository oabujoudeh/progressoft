package org.task4;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter equation: ");
            String equation = scanner.nextLine();

            // one shared Scanner passed to every processor instead of each processor opening its own
            EquationProcessor processor = new EquationProcessor(scanner);

            try {
                double result = processor.process(equation);
                System.out.println("Result: " + result);
            } catch (RuntimeException e) {
                // invalid equations (mismatched parens, missing operand/operator, divide by
                // zero, etc.) are now reported and the loop continues instead of crashing
                System.out.println("Invalid equation: " + e.getMessage());
            }

        }

    }
}
