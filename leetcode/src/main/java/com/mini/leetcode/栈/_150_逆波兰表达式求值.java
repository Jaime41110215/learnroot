package com.mini.leetcode.栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String str : tokens) {
            if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)) {
                String b = stack.pop();
                String a = stack.pop();
                if ("+".equals(str)) stack.push(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b)));
                if ("-".equals(str)) stack.push(String.valueOf(Integer.parseInt(a) - Integer.parseInt(b)));
                if ("*".equals(str)) stack.push(String.valueOf(Integer.parseInt(a) * Integer.parseInt(b)));
                if ("/".equals(str)) stack.push(String.valueOf(Integer.parseInt(a) / Integer.parseInt(b)));
            } else {
                stack.push(str);
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
