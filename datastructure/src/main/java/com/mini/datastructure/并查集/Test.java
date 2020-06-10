package com.mini.datastructure.并查集;

import java.util.Arrays;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public class Test {

    public static void main(String[] args) {
//        UnionFind uf = new QuickFind(12);
//        test(new QuickFind(12));
//        test(new QuickUnion_减半(12));
        ListUnionFind<Integer> uf = new ListUnionFind<>();
        test(uf);
    }


    public static void test(ListUnionFind uf){

        uf.union(0,1);
        uf.union(1,2);
        uf.union(2,3);
        uf.union(3,4);
        uf.union(4,5);

        uf.union(6,7);

        uf.union(8,9);
        uf.union(9,10);
        uf.union(10,11);

        System.out.println(uf.isSame(4,7));
        System.out.println(uf.isSame(7,9));
        System.out.println(uf.isSame(1,5));

        uf.union(5,7);
        uf.union(6,9);
        System.out.println("=================================");
        System.out.println(uf.isSame(4,7));
        System.out.println(uf.isSame(7,9));
        System.out.println(uf.isSame(1,5));
    }


}
