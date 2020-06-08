package com.mini.datastructure.并查集;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public class QuickUnion implements UnionFind {

    private int[] parents;

    public QuickUnion(int cap){
        if(cap < 1) throw new IllegalArgumentException("cap < 1");
        this.parents = new int[cap];
        for (int i = 0; i < cap; i++) {
            parents[i] = i;
        }
    }

    @Override
    public int find(int val) {
        while (val != parents[val]){
            val = parents[val];
        }
        return val;
    }

    @Override
    public void union(int v1, int v2) {
        int f1 = find(v1);
        int f2 = find(v2);
        if(f1 == f2) return;

        parents[f1] = f2;
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
