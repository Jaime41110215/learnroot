package com.mini.leetcode.栈;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class _224_基本计算器 {
    public static void main(String[] args) {
        int calculate = new _224_基本计算器().calculate2("(1+(4+5+2)-3)+(6+8)");
        System.out.println(calculate);
    }
    //(1+(4+5+2)-3)+(6+8)

    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        int sign = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                operand = operand * 10 + (ch - '0');
            }else if(ch == '+') {
                result += sign * operand;
                operand = 0;
                sign = 1;
            }else if(ch == '-'){
                result += sign * operand;
                operand = 0;
                sign = -1;
            }else if(ch == '('){
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }else if(ch == ')'){
                result += sign * operand;
                operand = 0;
                result *= stack.pop();
                result += stack.pop();
            }
        }
        return result + (sign * operand);
    }
    //(1+(4+5+2)-3)+(6+8)
    public int calculate(String s) {
        Stack<Object> stack = new Stack<>();
        int operand = 0;
        int n = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == ' '){
                continue;
            }
            if(Character.isDigit(c)){
                operand = (int)Math.pow(10,n) * (c - '0') + operand;
                n++;
            }else {
                if(n != 0){
                    stack.push(operand);
                    n = 0;
                    operand = 0;
                }
                if(c == '('){
                    int result = 0;
                    int cal = 1;
                    Object obj = null;
                    while (!((obj=stack.peek()) instanceof Character && ((char)obj) == ')')){
                        Object pop = stack.pop();
                        if(pop instanceof Character){
                            char ch = (char)pop;
                            if(ch == '+'){
                                cal = 1;
                            }else{
                                cal = -1;
                            }
                        }else {
                            result += cal * (int)pop;
                        }
                    }
                    stack.pop();
                    stack.push(result);
                }else {
                    stack.push(c);
                }

            }
        }

        if(n != 0){
            stack.push(operand);
        }

        int result = 0;
        int cal = 1;
        while (!stack.isEmpty()){
            Object pop = stack.pop();
            if(pop instanceof Character){
                char ch = (char)pop;
                if(ch == '+'){
                    cal = 1;
                }else{
                    cal = -1;
                }
            }else {
                result += cal * (int)pop;
            }
        }


        return result;
    }

    class Solution {

            public int evaluateExpr(Stack<Object> stack) {

                int res = 0;

                if (!stack.empty()) {
                    res = (int) stack.pop();
                }

                // Evaluate the expression till we get corresponding ')'
                while (!stack.empty() && !((char) stack.peek() == ')')) {

                    char sign = (char) stack.pop();

                    if (sign == '+') {
                        res += (int) stack.pop();
                    } else {
                        res -= (int) stack.pop();
                    }
                }
                return res;
            }

            public int calculate(String s) {

                int operand = 0;
                int n = 0;
                Stack<Object> stack = new Stack<Object>();

                for (int i = s.length() - 1; i >= 0; i--) {

                    char ch = s.charAt(i);

                    if (Character.isDigit(ch)) {

                        // Forming the operand - in reverse order.
                        operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
                        n += 1;

                    } else if (ch != ' ') {
                        if (n != 0) {

                            // Save the operand on the stack
                            // As we encounter some non-digit.
                            stack.push(operand);
                            n = 0;
                            operand = 0;

                        }
                        if (ch == '(') {

                            int res = evaluateExpr(stack);
                            stack.pop();

                            // Append the evaluated result to the stack.
                            // This result could be of a sub-expression within the parenthesis.
                            stack.push(res);

                        } else {
                            // For other non-digits just push onto the stack.
                            stack.push(ch);
                        }
                    }
                }

                //Push the last operand to stack, if any.
                if (n != 0) {
                    stack.push(operand);
                }

                // Evaluate any left overs in the stack.
                return evaluateExpr(stack);
            }
    }

    class Solution2 {
        public int calculate(String s) {

            Stack<Integer> stack = new Stack<Integer>();
            int operand = 0;
            int result = 0; // For the on-going result
            int sign = 1;  // 1 means positive, -1 means negative

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    operand = 10 * operand + (int) (ch - '0');
                } else if (ch == '+') {
                    result += sign * operand;
                    sign = 1;
                    operand = 0;
                } else if (ch == '-') {
                    result += sign * operand;
                    sign = -1;
                    operand = 0;
                } else if (ch == '(') {
                    stack.push(result);
                    stack.push(sign);
                    sign = 1;
                    result = 0;
                } else if (ch == ')') {
                    result += sign * operand;
                    result *= stack.pop();
                    result += stack.pop();
                    operand = 0;
                }
            }
            return result + (sign * operand);
        }
    }


}
