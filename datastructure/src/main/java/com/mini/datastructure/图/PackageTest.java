package com.mini.datastructure.图;

import java.util.List;
import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/9
 */
public class PackageTest {

    @Test
    public void testGraph(){
        ListGraph<String,Integer> listGraph = new ListGraph<>();
        listGraph.addEdge("A","B");
        listGraph.addEdge("A","D");

        listGraph.addEdge("B","F");

        listGraph.addEdge("C","B");
        listGraph.addEdge("C","F");

        listGraph.addEdge("E","A");
        listGraph.addEdge("E","B");
        listGraph.addEdge("E","F");

        List<String> list = listGraph.topologicalSort();
        System.out.println(list);
    }


    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
