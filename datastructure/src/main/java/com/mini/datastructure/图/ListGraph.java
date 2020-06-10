package com.mini.datastructure.图;

import com.mini.datastructure.并查集.ListUnionFind;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author wanghongchao
 * @time 2020/6/9
 */
public class ListGraph<V,E extends Comparable<E>> implements Graph<V,E> {

    /**
     * 顶点 v->vertex
     */
    private final Map<V,Vertex<V,E>> vertices = new HashMap<>();
    /**
     * 所有边
     */
    private final Set<Edge<V,E>> edges = new HashSet<>();

    private WeightManager<E> weightManager;


    public ListGraph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    /**
     * 获取顶点数量
     * @return
     */
    @Override
    public int verticesSize() {
        return vertices.size();
    }

    /**
     * 获取边数量
     * @return
     */
    @Override
    public int edgesSize() {
        return edges.size();
    }

    /**
     * 添加顶点
     * @param val
     */
    @Override
    public void addVertex(V val) {
        vertices.put(val,new Vertex<>(val));
    }

    /**
     * 删除顶点
     * @param val
     */
    @Override
    public void removeVertex(V val) {
        Vertex<V, E> vertex = vertices.remove(val);
        if(vertex == null) return;
        System.out.println();
        Iterator<Edge<V, E>> outIterator = vertex.outEdges.iterator();
        while (outIterator.hasNext()){
            Edge<V, E> next = outIterator.next();
            Vertex<V, E> to = vertices.get(next.to);
            to.inEdges.remove(next);
            edges.remove(next);
            outIterator.remove();
        }

        Iterator<Edge<V, E>> inIterator = vertex.inEdges.iterator();
        while (inIterator.hasNext()){
            Edge<V, E> next = inIterator.next();
            Vertex<V, E> from = vertices.get(next.from);
            from.outEdges.remove(next);
            edges.remove(next);
            inIterator.remove();
        }
    }

    /**
     * 添加边
     * @param from
     * @param to
     */
    @Override
    public void addEdge(V from, V to) {
        addEdge(from,to,null);
    }

    /**
     * 添加边
     * @param from
     * @param to
     * @param weight
     */
    @Override
    public void addEdge(V from, V to, E weight) {
        if(!vertices.containsKey(from)){
            addVertex(from);
        }
        if(!vertices.containsKey(to)){
            addVertex(to);
        }
        Edge<V,E> edge = new Edge<>(from,to,weight);
        //from->out
        if(vertices.get(from).outEdges.remove(edge)){
            vertices.get(to).inEdges.remove(edge);
            edges.remove(edge);
        }
        vertices.get(from).outEdges.add(edge);
        vertices.get(to).inEdges.add(edge);
        edges.add(edge);
    }

    /**
     * 删除边
     * @param from
     * @param to
     */
    @Override
    public void removeEdge(V from, V to) {
        Edge<V,E> edge = new Edge<>(from,to,null);
        vertices.get(from).outEdges.remove(edge);
        vertices.get(to).inEdges.remove(edge);
        edges.remove(edge);
    }

    private Set<Vertex<V,E>> set = new HashSet<>();

    /**
     * bfs 广度优先
     */
    public void bfs(V val){
        Vertex<V, E> vertex = vertices.get(val);
        if(vertex == null) return;
        set.clear();
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        queue.offer(vertex);
        set.add(vertex);
        while (!queue.isEmpty()){
            Vertex<V, E> poll = queue.remove();
            System.out.println(poll.val);
            poll.outEdges.forEach(edge->{
                Vertex<V, E> tmp = vertices.get(edge.to);
                if(!set.contains(tmp)){
                    queue.offer(tmp);
                    set.add(tmp);
                }
            });
        }
    }


    /**
     * 深度优先
     */
    public void dfs(V val){
        Vertex<V, E> vertex = vertices.get(val);
        if(vertex == null) return;
        set.clear();
        dfs(vertex);
        System.out.println();
    }

    public void dfs(Vertex<V,E> vertex){
        System.out.print(vertex.val + "\t");
        set.add(vertex);
        vertex.outEdges.forEach(edge->{
            if(!set.contains(vertices.get(edge.to))){
                dfs(vertices.get(edge.to));
            }
        });
    }


    public void dfs2(V val){
        Vertex<V, E> vertex = vertices.get(val);
        if(vertex == null) return;
        set.clear();
        dfs2(vertex);
    }

    public void dfs2(Vertex<V,E> vertex){
        Stack<Vertex<V,E>> stack = new Stack<>();
        stack.push(vertex);
        while (!stack.isEmpty()){
            Vertex<V, E> pop = stack.pop();
            if(set.contains(pop)){
                continue;
            }else {
                System.out.print(pop.val + "\t");
                set.add(pop);
            }
            pop.outEdges.forEach(edge->{
                stack.push(vertices.get(edge.to));
            });
        }
        System.out.println();
    }

    public List<V> topologicalSort(){
        List<V> resultList = new ArrayList<>();
        //入度表
        Map<Vertex<V,E>,Integer> inMap = new HashMap<>();
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        //Vertex<V, E> zeroIn = null;
        vertices.forEach((key,val)->{
            if(val.inEdges.size() == 0){
                queue.offer(val);
            }else {
                inMap.put(val,val.inEdges.size());
            }
        });

        while (!queue.isEmpty()){
            Vertex<V, E> zeroIn = queue.poll();
            //inMap.remove(zeroIn);
            resultList.add(zeroIn.val);
            //更新入度表
            zeroIn.outEdges.forEach(edge->{
                Vertex<V, E> vertex = vertices.get(edge.to);
                int newCount = inMap.get(vertex) - 1;
                if(newCount == 0){
                    queue.offer(vertex);
                    inMap.remove(vertex);
                }else {
                    inMap.put(vertex,inMap.get(vertex)-1);
                }
            });
        }
        return resultList;
    }

    public List<EdgeInfo<V,E>> prim(){
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        Vertex<V, E> vertex = null;
        if(iterator.hasNext()) vertex =iterator.next();
        if(vertex == null) return null;

        List<EdgeInfo<V,E>> result = new ArrayList<>();
        List<Vertex<V,E>> addedVertices = new ArrayList<>();
        addedVertices.add(vertex);
        PriorityQueue<Edge<V,E>> minHeap = new PriorityQueue<>(vertex.outEdges);

        while (!minHeap.isEmpty() && result.size() < vertices.size()-1){
            Edge<V, E> poll = minHeap.poll();
            if(addedVertices.contains(vertices.get(poll.to))){
                continue;
            }
            result.add(poll.getEdgeInfo());
            addedVertices.add(vertices.get(poll.to));
            minHeap.addAll(vertices.get(poll.to).outEdges);
        }

        return result;
    }

    public List<EdgeInfo<V,E>> kruskal(){
        List<EdgeInfo<V,E>> result = new ArrayList<>();
        PriorityQueue<Edge<V,E>> queue = new PriorityQueue<>(edges);
        Collection<Vertex<V, E>> values = vertices.values();
        List<V> collect = values.stream().map(vertex -> vertex.val).collect(Collectors.toList());
        ListUnionFind<V> unionFind = new ListUnionFind<>(collect);
        while (!queue.isEmpty() && result.size() < vertices.size()-1){
            Edge<V, E> poll = queue.poll();
            if(unionFind.isSame(poll.from,poll.to)) continue;
            result.add(poll.getEdgeInfo());
            unionFind.union(poll.from,poll.to);
        }

        return result;
    }


    public Map<V,E> dijkstra(V val){
        Vertex<V, E> vertex = vertices.get(val);
        if(vertex == null) return null;
        Map<V,E> resultMap = new HashMap<>();
        Map<Vertex<V,E>,E> tmp = new HashMap<>();
        vertex.outEdges.forEach(edge->{
            tmp.put(vertices.get(edge.to),edge.weight);
        });

        while (!tmp.isEmpty()){
            Vertex<V, E> minVertex = getMinVertex(tmp);
            resultMap.put(minVertex.val,tmp.get(minVertex));
            E pw = tmp.get(minVertex);
            tmp.remove(minVertex);
            for (Edge<V,E> edge : minVertex.outEdges) {
                Vertex<V, E> oldVertex = vertices.get(edge.to);
                if(tmp.containsKey(oldVertex)){
                    //松弛操作
                    E oldWeight = tmp.get(oldVertex);
                    E newWeight = weightManager.add(pw,edge.weight);
                    if(newWeight.compareTo(oldWeight)<0){
                        tmp.put(oldVertex,newWeight);
                    }
                }else {
                    tmp.put(vertices.get(edge.to),weightManager.add(pw,edge.weight));
                }
            }
        }
        return resultMap;
    }

    private Vertex<V,E> getMinVertex(Map<Vertex<V,E>,E> tmp){
        Vertex<V,E> vertex;
        E weight;
        Iterator<Entry<Vertex<V, E>, E>> iterator = tmp.entrySet().iterator();
        if(iterator.hasNext()){
            Entry<Vertex<V, E>, E> next = iterator.next();
            vertex = next.getKey();
            weight = next.getValue();
        }else {
            return null;
        }
        while (iterator.hasNext()){
            Entry<Vertex<V, E>, E> next = iterator.next();
            if(next.getValue().compareTo(weight) < 0){
                vertex = next.getKey();
                weight = next.getValue();
            }
        }
        return vertex;
    }



    public static class EdgeInfo<V,E> {
        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }





    private static class Vertex<V,E extends Comparable<E>> {
        V val;
        Set<Edge<V,E>> outEdges = new HashSet<>();
        Set<Edge<V,E>> inEdges = new HashSet<>();

        Vertex(V val){
            this.val = val;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "val=" + val +
                    ", outEdges=" + outEdges +
                    ", inEdges=" + inEdges +
                    '}';
        }
    }

    private static class Edge<V,E extends Comparable<E>> implements Comparable<Edge<V,E>> {
        V from;
        V to;
        E weight;

        Edge(V from,V to,E weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Edge{" + from + "=>" + to + ":" + weight + "}";
        }

        public EdgeInfo<V,E> getEdgeInfo(){
            EdgeInfo<V,E> edgeInfo = new EdgeInfo<>(from,to);
            edgeInfo.weight = weight;
            return edgeInfo;
        }

        @Override
        public int compareTo(Edge<V, E> o) {
            return weight.compareTo(o.weight);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collection<Vertex<V, E>> values = vertices.values();
        values.forEach(val-> sb.append(val).append("\r\n"));
        return sb.toString();
    }


    public interface WeightManager<E> {
        E add(E e1,E e2);
    }
}
