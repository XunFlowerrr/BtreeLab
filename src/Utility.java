import java.util.ArrayList;

public class Utility {
    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    public static int precedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        } else if (token.equals("*") || token.equals("/")) {
            return 2;
        } else if (token.equals("^")) {
            return 3;
        } else {
            return 0;
        }
    }

    public static int getClosureIndex(ArrayList<String> expression, int start) {
        int end = 0;
        int openParenthesis = 1;
        int closeParenthesis = 0;

        for (int j = start + 1; j < expression.size(); j++) {
            if (expression.get(j).equals("(")) {
                openParenthesis++;
            } else if (expression.get(j).equals(")")) {
                closeParenthesis++;
            }
            if (openParenthesis == closeParenthesis) {
                end = j;
                break;
            }
        }
        return end;
    }


}
