package com.company;

import java.util.Stack;

public class Calculator {
    //Convert infix expression to postfix using stack
    public String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<Character>();
        char[] exp = expression.toCharArray();
        StringBuilder postfix = new StringBuilder();

        for(char curr : exp) {
            if(Character.isDigit(curr) || curr == '.')
                postfix.append(curr);
            else if(isOperator(curr)) {
                while(!stack.empty() && (getPrecedence(stack.peek()) >= getPrecedence(curr)))
                    postfix.append(stack.pop());
                stack.push(curr);
            }
            else if(curr == '(')
                stack.push(curr);
            else if(curr == ')') {
                while(!stack.empty() && stack.peek() != '(')
                    postfix.append(stack.pop());
                stack.pop();
            }
        }
        while(!stack.empty())
            postfix.append(stack.pop());
        return Double.toString(calcPostfix(postfix.toString()));
    }

    //Check if character is operator
    private boolean isOperator(char op) {
        switch(op) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    //Retrieve precedence for order of operations
    private int getPrecedence(char op) {
        switch(op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    /*Create a new stack and array
    * Store postfix expression into array and push elements onto stack
    * The expression should be ordered with correct precedence.
    * Proceed with calculations via popping from stack.
    * */
    private double calcPostfix(String postfix) {
        Stack<Double> stack = new Stack<Double>();
        char[] exp = postfix.toCharArray();

        double rOperand, lOperand;
        double result = 0;

        for(char curr : exp) {
            if(Character.isDigit(curr))
                stack.push(Double.parseDouble(Character.toString(curr)));
            else if(isOperator(curr)) {
                rOperand = stack.pop();
                lOperand = stack.pop();
                switch(curr) {
                    case '+':
                        result = lOperand + rOperand;
                        stack.push(result);
                        break;
                    case '-':
                        result = lOperand - rOperand;
                        stack.push(result);
                        break;
                    case '*':
                        result = lOperand * rOperand;
                        stack.push(result);
                        break;
                    case '/':
                        if(rOperand == 0)
                            throw new
                                    ArithmeticException("CANNOT DIVIDE BY 0");
                        else
                            result = lOperand / rOperand;
                        stack.push(result);
                        break;
                    case '^':
                        result = Math.pow(lOperand, rOperand);
                        stack.push(result);
                        break;
                } //End switch case
            } //End else if
        } //End for
        return result;
    }
}
