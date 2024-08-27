import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class IOHandler {


    public static ArrayList<String> getTokens(String expression) {

        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
            } else if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    sb.append(expression.charAt(++i));
                }
                tokens.add(sb.toString());
            } else if (c == '-' && i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    sb.append(expression.charAt(++i));
                }
                tokens.add(sb.toString());
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                tokens.add(String.valueOf(c));
            }
        }
        return tokens;
    }


    public static String formatOutput(double result) {
        BigDecimal bd = new BigDecimal(result).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros();
        if (bd.scale() <= 0) {
            return bd.toBigInteger().toString();
        } else {
            return bd.toPlainString();
        }
    }
}
