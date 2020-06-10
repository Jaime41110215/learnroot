package com.mini.datastructure.图;

import com.mini.datastructure.图.ListGraph.EdgeInfo;
import com.mini.datastructure.图.ListGraph.WeightManager;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/9
 */
public class PackageTest {



    @Test
    public void testGraph(){

        Graph<Object, Double> objectDoubleGraph = undirectedGraph(Data.MST_01);
        List<EdgeInfo<Object, Double>> prim = objectDoubleGraph.prim();
        List<EdgeInfo<Object, Double>> kruskal = objectDoubleGraph.kruskal();

        System.out.println(prim);

        System.out.println("=========================================================");

        System.out.println(kruskal);

        System.out.println("==========================================================");

    }

    @Test
    public void testtopological(){
        ListGraph<String,Double> listGraph = new ListGraph<>(weightManager);
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

    @Test
    public void testDijkstra(){
        ListGraph<String,Double> graph = new ListGraph<>(weightManager);
        graph.addEdge("A","E",100d);
        graph.addEdge("A","D",30d);
        graph.addEdge("A","B",10d);
        graph.addEdge("B","C",50d);
        graph.addEdge("C","E",10d);
        graph.addEdge("D","C",20d);
        graph.addEdge("D","E",60d);
        Map<String, Double> a = graph.dijkstra("A");
        System.out.println(a);

    }

    public static WeightManager<Double> weightManager = (e1,e2)-> e1+e2;

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
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
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
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
