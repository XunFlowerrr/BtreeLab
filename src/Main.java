import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String expression;

        try (Scanner input = new Scanner(System.in)) {
            expression = input.nextLine();
        }

        BTreeMath bTreeMath = new BTreeMath(IOHandler.getTokens(expression));
        System.out.println(bTreeMath.evaluate());

    }
}
