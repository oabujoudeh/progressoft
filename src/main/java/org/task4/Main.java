package org.task4;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter equation: ");
        String equation = scanner.nextLine();

        EquationProcessor processor = new EquationProcessor();

        try {
            double result = processor.process(equation);
            System.out.println("Result: " + result);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}