import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // public static void main(String[] args) {
    //
    // String expression;
    //
    // try (Scanner input = new Scanner(System.in)) {
    // expression = input.nextLine();
    // }
    //
    // BTreeMath bTreeMath = new BTreeMath(IOHandler.getTokens(expression));
    // System.out.println(bTreeMath.evaluate());
    // }

    public static void main(String[] args) {
        ArrayList<String> expressions = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();

        // read from file multiple lines
        try (Scanner input = new Scanner(new File("input.txt"))) {
            while (input.hasNextLine()) {
                String expression = input.nextLine();
                expressions.add(expression);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner input = new Scanner(new File("key.txt"))) {
            while (input.hasNextLine()) {
                String key = input.nextLine();
                keys.add(key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < expressions.size(); i++) {
            BTreeMath bTreeMath = new BTreeMath(IOHandler.getTokens(expressions.get(i)));
            String result = bTreeMath.evaluate();
            System.out.println("Expression: " + expressions.get(i));
            System.out.println("Expected: " + keys.get(i));
            System.out.println("Result: " + result);
            // console color
            if (result.equals(keys.get(i))) {
                System.out.println("\u001B[32m" + "Correct" + "\u001B[0m");
            } else {
                System.out.println("\u001B[31m" + "Incorrect" + "\u001B[0m");
            }
            System.out.println();
        }
    }

}
