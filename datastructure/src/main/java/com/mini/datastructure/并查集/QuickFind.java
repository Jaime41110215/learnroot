package com.mini.datastructure.并查集;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public class QuickFind implements UnionFind {

    private int[] parents;

    public QuickFind(int cap){
        if(cap < 1) throw new IllegalArgumentException("cap < 1");
        this.parents = new int[cap];
        for (int i = 0; i < cap; i++) {
            parents[i] = i;
        }
    }

    /**
     * 根节点
     * @param val
     * @return
     */
    @Override
    public int find(int val) {
        rangeCheck(val);
        return parents[val];
    }

    private void rangeCheck(int val){
        if(val < 0 || val >= parents.length){
            throw new IllegalArgumentException();
        }
    }
    /**
     * 合并根节点
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        int f1 = find(v1);
        int f2 = find(v2);

        if(f1 == f2) return;

        for (int i = 0; i < parents.length; i++) {
            if(parents[i] == f1){
                parents[i] = f2;
            }
        }
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }
}
