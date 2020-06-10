package com.mini.datastructure.图;

import com.mini.datastructure.图.ListGraph.EdgeInfo;
import java.util.List;
import java.util.Map;

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

    List<EdgeInfo<V,E>> prim();

    List<EdgeInfo<V,E>> kruskal();

    Map<V,E> dijkstra(V val);
}
