package com.mini.datastructure.并查集;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public class QuickUnion_S implements UnionFind {

    private int[] parents;
    private int[] size;

    public QuickUnion_S(int cap){
        if(cap < 1) throw new IllegalArgumentException("cap < 1");
        parents = new int[cap];
        size = new int[cap];
        for (int i = 0; i < cap; i++) {
            parents[i] = i;
            size[i] = 1;
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

        if(size[f1] < size[f2]){
            parents[f1] = f2;
            size[f2]++;
        }else {
            parents[f2] = f1;
            size[f1]++;
        }
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
