package com.mini.algorithm.递归;

import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/11
 */
public class 斐波那契 {

    public int fib(int n){
        if(n <= 2) return 1;
        int first=1,second=1;
        for (int i=3; i <= n; i++){
            second = first + second;
            first = second - first;
        }
        return second;
    }
    // 1 1 2 3 5 8 13
    @Test
    public void testFib(){
        for (int i = 0; i < 10; i++) {
            System.out.println("fib("+i +")=" + fib(i));
        }
//        System.out.println(fib(47));
    }

    public int fib2(int n){
        if(n <= 2){
            return 1;
        }
        return fib2(n-1) + fib2(n-2);
    }



}
