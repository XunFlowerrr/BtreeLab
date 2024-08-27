import java.util.ArrayList;

public class BTreeMath {
    private final Node root;

    public BTreeMath() {
        root = null;
    }

    public BTreeMath(ArrayList<String> expression) {
        root = buildTree(expression);
    }

    private Node buildTree(ArrayList<String> expression) {
        if (expression.isEmpty()) {
            return null;
        }


        if (expression.get((0)).equals("(")) {
            int end = Utility.getClosureIndex(expression, 0);

            if (end + 2 <= expression.size()) {
                ArrayList<String> leftSubExpression = new ArrayList<>(expression.subList(1, end));
                ArrayList<String> rightSubExpression = new ArrayList<>(expression.subList(end + 2, expression.size()));
                Node right = buildTree(rightSubExpression);
                Node left = buildTree(leftSubExpression);
                Node root = new Node(expression.get(end + 1));
                root.setLeft(left);
                root.setRight(right);
                return root;
            }

            ArrayList<String> subExpression = new ArrayList<>(expression.subList(1, end));
            return buildTree(subExpression);


        }

        Node root = new Node();

        boolean isFirst = true;

        for (int i = 0; i < expression.size(); i++) {
            if (!Utility.isOperator(expression.get(i))) {
                if (isFirst) {
                    root.setValue(expression.get(i));
                    isFirst = false;
                } else {
                }


            } else {
                Node subRoot = root;

                Node newRoot = new Node(expression.get(i));

                if (!Utility.isOperator(subRoot.getValue()) || Utility.precedence(expression.get(i)) <= Utility.precedence(subRoot.getValue())) {
                    newRoot.setLeft(subRoot);

                    if (i + 1 < expression.size() && (!Utility.isOperator(expression.get(i + 1)) && !expression.get(i + 1).equals("("))) {
                        Node right = new Node(expression.get(i + 1));
                        newRoot.setRight(right);
                        i++;
                    } else if (i + 1 < expression.size() && expression.get(i + 1).equals("(")) {

                        int end = Utility.getClosureIndex(expression, i + 1);


                        ArrayList<String> subExpression = new ArrayList<>(expression.subList(i + 2, end));
                        Node right = buildTree(subExpression);
                        newRoot.setRight(right);
                        i = end;
                    }
                } else {
                    newRoot.setLeft(subRoot.getRight());
                    if (i + 1 < expression.size() && (!Utility.isOperator(expression.get(i + 1)) && !expression.get(i + 1).equals("("))) {
                        Node right = new Node(expression.get(i + 1));
                        newRoot.setRight(right);
                        i++;
                    } else if (i + 1 < expression.size() && expression.get(i + 1).equals("(")) {

                        int end = Utility.getClosureIndex(expression, i + 1);


                        ArrayList<String> subExpression = new ArrayList<>(expression.subList(i + 2, end));
                        Node right = buildTree(subExpression);
                        newRoot.setRight(right);
                        i = end;
                    }
                    subRoot.setRight(newRoot);
                    newRoot = subRoot;

                }

                root = newRoot;


            }

        }

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
