package com.mini.leetcode.队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */

public class _225_用队列实现栈 {
    private Queue<Integer> in = new LinkedList<>();
    private Queue<Integer> out = new LinkedList<>();
    /** Initialize your data structure here. */
    public _225_用队列实现栈() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        in.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int result = -1;
        while (!in.isEmpty()){
            int tmp = in.poll();
            if(in.isEmpty()){
                result = tmp;
            }else {
                out.offer(tmp);
            }
        }
        Queue<Integer> tmp = in;
        in = out;
        out = tmp;
        return result;
    }

    /** Get the top element. */
    public int top() {
        int result = -1;
        while (!in.isEmpty()){
            int tmp = in.poll();
            if(in.isEmpty()){
                result = tmp;
            }
            out.offer(tmp);
        }
        Queue<Integer> tmp = in;
        in = out;
        out = tmp;
        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in.isEmpty();
    }

    public static void main(String[] args) {
        _225_用队列实现栈 stack = new _225_用队列实现栈();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());;
        System.out.println(stack.top());;
    }
}
