import java.util.ArrayList;

public class BTreeMath {
    private final Node root;

    public BTreeMath(ArrayList<String> expression) {
        root = buildTree(expression);
    }

    private Node buildTree(ArrayList<String> expression) {
        return buildTreeHelper(expression, 0, expression.size() - 1);
    }

    private Node buildTreeHelper(ArrayList<String> expression, int start, int end) {
        if (start > end) {
            return null;
        }
        // Find the operator with the lowest precedence
        int minPrecedence = Integer.MAX_VALUE;
        int minPrecedenceIndex = -1;
        int parenthesesCount = 0;
        for (int i = start; i <= end; i++) {
            String token = expression.get(i);
            if (token.equals("(")) {
                parenthesesCount++;
            } else if (token.equals(")")) {
                parenthesesCount--;
            } else if (Utility.isOperator(token) && parenthesesCount == 0) {
                int precedence = Utility.precedence(token);
                if (precedence <= minPrecedence) {
                    minPrecedence = precedence;
                    minPrecedenceIndex = i;
                }
            }
        }
        // If no operator found, it's either a single number or a parenthesized expression
        if (minPrecedenceIndex == -1) {
            if (expression.get(start).equals("(") && expression.get(end).equals(")")) {
                return buildTreeHelper(expression, start + 1, end - 1);
            } else {
                return new Node(expression.get(start));
            }
        }
        // Create a node for the operator
        Node root = new Node(expression.get(minPrecedenceIndex));
        // Recursively build left and right subtrees
        root.setLeft(buildTreeHelper(expression, start, minPrecedenceIndex - 1));
        root.setRight(buildTreeHelper(expression, minPrecedenceIndex + 1, end));
        return root;
    }

    public String evaluate() {
        return IOHandler.formatOutput(evaluate(root));
    }

    private double evaluate(Node root) {
        if (root.isLeaf()) {
            return Double.parseDouble(root.getValue());
        }
        double left = evaluate(root.getLeft());
        double right = evaluate(root.getRight());
        return switch (root.getValue()) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            case "^" -> Math.pow(left, right);
            default -> 0;
        };
    }

}
