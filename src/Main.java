import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = sc.nextLine();
        Calculator calc = new Calculator(expression);
        calc.calculate();
    }
}