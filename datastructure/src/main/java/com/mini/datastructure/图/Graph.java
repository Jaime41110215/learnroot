package com.mini.datastructure.图;

/**
 * @author wanghongchao
 * @time 2020/6/9
 */
public interface Graph<V,E extends Comparable> {
    int verticesSize();
    int edgesSize();

    void addVertex(V val);
    void removeVertex(V val);

    void addEdge(V from,V to);
    void addEdge(V from,V to,E weight);

    void removeEdge(V from,V to);
}
