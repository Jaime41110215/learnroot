package com.mini.datastructure.并查集;

/**
 * @author wanghongchao
 * @time 2020/6/4
 */
public interface UnionFind {
    int find(int val);
    void union(int v1, int v2);
    boolean isSame(int v1, int v2);
}
