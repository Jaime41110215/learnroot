package com.mini.datastructure.并查集;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public class QuickUnion_R implements UnionFind {

    private int[] parents;
    private int[] rank;

    public QuickUnion_R(int cap){
        parents = new int[cap];
        rank = new int[cap];

        for (int i = 0; i < cap; i++) {
            parents[i] = i;
            rank[i] = 1;
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

        if(rank[f1] > rank[f2]){
            parents[f2] = f1;
        }else if(rank[f1] < rank[f2]){
            parents[f1] = f2;
        }else{
            parents[f1] = f2;
            rank[f2]++;
        }
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
