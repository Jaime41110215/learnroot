package com.mini.leetcode.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/hanota-lcci/
 * @author wanghongchao
 * @time 2020/6/12
 */
public class _面试题_08_06_汉诺塔问题 {

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(),A,B,C);
    }

    public void hanota(int n,List<Integer> A, List<Integer> B, List<Integer> C) {
        if(A == null || A.size() == 0) return;
        if(n == 1){
            C.add(A.remove(A.size()-1));
            return;
        }
        //n-1 个盘子从A-B
        hanota(n-1,A,C,B);
        //第n个盘子 从A-C
        C.add(A.remove(A.size()-1));
        //n-1 个盘子从B - C
        hanota(n-1,B,A,C);
    }

    @Test
    public void test(){
        List<Integer> A = new ArrayList<>(Arrays.asList(4,3,2,1,0));
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();

        hanota(A,B,C);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

    }

}
