package com.example.calcul;

import java.util.Collections;
import java.util.ListIterator;
import java.util.Stack;

public class ExpressionParser2 {

    final String[] OPERATORS = {"+","-","/","*"};
    final String[] FUNCTIONS = {"M","C","M+","M-","sin","exp","sqrt"};

    protected Stack<String> stackOperations = new Stack<>();
    protected Stack<String> stackPOLIZ = new Stack<>();
    protected Stack<String> stackCalc = new Stack<>();

    public ExpressionParser2() {
        stackOperations.clear();
        stackPOLIZ.clear();
        stackCalc.clear();
    }

    public double calculate(ListIterator<String> InputData) {
        convertToRPN(InputData);
        Collections.reverse(stackPOLIZ);
        stackCalc.clear();
        while (!stackPOLIZ.empty()) {
            String token = stackPOLIZ.pop();
            if (isNumber(token)) {
                stackCalc.push(token);
            } else if (isOperator(token)) {

                double operand1 = Double.parseDouble(stackCalc.pop());
                double operand2 = Double.parseDouble(stackCalc.pop());

                switch (token) {
                    case "+":
                        stackCalc.push(String.valueOf(operand2+operand1));
                        break;
                    case "-":
                        stackCalc.push(String.valueOf(operand2-operand1));
                        break;
                    case "*":
                        stackCalc.push(String.valueOf(operand2*operand1));
                        break;
                    case "/":
                        stackCalc.push(String.valueOf(operand2/operand1));
                        break;
                }
            } else if (isFunction(token)) {

                double operand = Double.parseDouble(stackCalc.pop());
                switch (token) {
                    case "sin":
                        stackCalc.push(String.valueOf(Math.sin(Math.toRadians(operand))));
                        break;
                    case "exp":
                        stackCalc.push(String.valueOf(Math.exp(operand)));
                        break;
                    case "sqrt":
                        stackCalc.push(String.valueOf(Math.sqrt(operand)));
                        break;
                }
            }
        }
        return Double.parseDouble(stackCalc.pop());
    }

    protected void convertToRPN(ListIterator<String> tokens) {

        while (tokens.hasNext()) {
            String token = tokens.next();
            if (isNumber(token)) {
                stackPOLIZ.push(token);
            } else if (isFunction(token)) {
                stackOperations.push(token);
            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && (definePriority(token)
                        <= definePriority(stackOperations.lastElement()))) {
                    stackPOLIZ.push(stackOperations.pop());
                }
                stackOperations.push(token);
            } else if (isLeftBracket(token)) {
                stackOperations.push(token);
            } else if (isRightBracket(token)) {
                while (!stackOperations.empty()
                        && !isLeftBracket(stackOperations.lastElement())) {
                    stackPOLIZ.push(stackOperations.pop());
                }

                stackOperations.pop();
                if (isFunction(stackOperations.lastElement())) {
                    stackPOLIZ.push(stackOperations.pop());
                }
            } else {
                stackOperations.clear();
                stackPOLIZ.clear();
                return ;
            }
        }

        while (!stackOperations.empty()) {
            stackPOLIZ.push(stackOperations.pop());
        }
    }

    public boolean isNumber (String token) {
        try {
            Double.parseDouble(token);
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }

    public boolean isOperator(String token) {
        for (String op : OPERATORS)
            if (op.equals(token))
                return true;
        return false;
    }



    public  boolean isFunction(String token) {
        for (String func : FUNCTIONS) {
            if (func.equalsIgnoreCase(token))
                return true;
        }
        return false;
    }

    protected boolean isLeftBracket(String token) {
        return token.equals("(");
    }

    protected boolean isRightBracket(String token) {
        return token.equals(")");
    }

    protected int definePriority(String operation) {
        if (operation.equals("+") || operation.equals("-"))
            return 1;
        else
            return 2;
    }


}
