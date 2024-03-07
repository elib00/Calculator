import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {
    ArrayList<String> expressionTokens = new ArrayList<String>();

    public Calculator(String expression) {
        StringTokenizer tokenizer = new StringTokenizer(expression);

        // Tokenizing the string and printing each token
        // Storing the tokens in the array list
        while (tokenizer.hasMoreTokens()) {
            expressionTokens.add(tokenizer.nextToken());
        }
    }

    public ArrayList<String> invert(Stack<String> orig) { // reverse the stack to preserve the original order
        ArrayList<String> arr = new ArrayList<>();
        Stack<String> dup = new Stack<>();
        while (orig.size() > 0) {
            dup.push(orig.pop());
        }

        while (dup.size() > 0) {
            arr.add(dup.pop());
        }

        for (String s : arr) {
            System.out.print(s + " ");
        }

        System.out.println();
        return arr;
    }

    public void calculate() {
        int len = expressionTokens.size();
        Stack<String> st1 = new Stack<>();
        Stack<String> st2 = new Stack<>();

        // for mult and div
        boolean found = false; // for skipping the number after an * or / operation
        for (int i = 0; i < len; i++) {
            if (found) {
                found = false;
                continue;
            }

            String curr = expressionTokens.get(i);
            if (i == len - 1) {
                st1.push(curr);
                break;
            }

            String after = expressionTokens.get(i + 1);
            if (curr.equals("*")) {
                double num1 = Double.parseDouble(st1.pop());
                double num2 = Double.parseDouble(after);
                st1.push(Double.toString(num1 * num2));
                found = true;
            } else if (curr.equals("/")) {
                double num1 = Double.parseDouble(st1.pop());
                double num2 = Double.parseDouble(after);
                if (Double.parseDouble(after) == 0) {
                    throw new ArithmeticException("Division by zero not allowed!");
                }
                st1.push(Double.toString(num1 / num2));
                found = true;
            } else {
                st1.push(curr);
            }
        }

        ArrayList<String> expression2 = invert(st1);
        len = expression2.size();
        found = false;

        // for sub and add
        for (int i = 0; i < len; i++) {
            if (found) {
                found = false;
                continue;
            }

            String curr = expression2.get(i);
            if (i == len - 1) {
                st1.push(curr);
                break;
            }

            String after = expression2.get(i + 1);
            if (curr.equals("+")) {
                double num1 = Double.parseDouble(st2.pop());
                double num2 = Double.parseDouble(after);
                st2.push(Double.toString(num1 + num2));
                System.out.println("Add: " + (num1 + num2));
                found = true;
            } else if (curr.equals("-")) {
                double num1 = Double.parseDouble(st2.pop());
                double num2 = Double.parseDouble(after);
                st2.push(Double.toString(num1 - num2));
                System.out.println("Sub: " + (num1 - num2));
                found = true;
            } else {
                st2.push(curr);
                System.out.println("Pushed: " + curr);
            }

        }
    }
}
