package com.mini.leetcode.栈;

import java.util.Stack;

/**
 *
 */
public class _20_有效的括号 {

    public boolean isValid(String s) {
        while (s.contains("{}")||s.contains("[]")||s.contains("()")){
            s = s.replace("{}","");
            s = s.replace("[]","");
            s = s.replace("()","");
        }
        return s.isEmpty();
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(curr == '('||curr == '{'||curr == '['){//左括号
                stack.push(curr);
            }else {//右括号
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                if(left == '(' && curr != ')') return false;
                if(left == '[' && curr != ']') return false;
                if(left == '{' && curr != '}') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()";

        new _20_有效的括号().isValid(s);
    }
}

