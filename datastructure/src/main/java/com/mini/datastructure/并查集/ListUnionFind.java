package com.mini.datastructure.并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wanghongchao
 * @time 2020/6/10
 */
public class ListUnionFind<V> {

    private Map<V,Node<V>> nodeMap = new HashMap<>();

    public ListUnionFind(List<V> list) {
        if(list == null || list.size()==0) throw new IllegalArgumentException();
        list.forEach(v->{
            nodeMap.put(v,new Node<>(v));
        });
    }
    public ListUnionFind() {

    }

    public V find(V val){
        Node<V> node = nodeMap.get(val);
        if(node == null) return null;
        Node<V> parent = find(node);
        return parent != null ? parent.val : null;
    }

    private Node<V> find(Node<V> node){
        while (node != node.parent){
            node = node.parent;
        }
        return node;
    }

    public void union(V v1,V v2){
        Node<V> n1 = nodeMap.get(v1);
        Node<V> n2 = nodeMap.get(v2);
        if(n1==null){
            n1 = new Node<>(v1);
            nodeMap.put(v1,n1);
        }
        if(n2 == null){
            n2 = new Node<>(v2);
            nodeMap.put(v2,n2);
        }
        Node<V> f1 = find(n1);
        Node<V> f2 = find(n2);
        if(f1 != f2){
            f1.parent = f2.parent;
        }
    }

    public boolean isSame(V v1,V v2){
        return Objects.equals(find(v1),find(v2));
    }



    private static class Node<V> {
        V val;
        Node<V> parent;

        Node(V val) {
            this.val = val;
            this.parent = this;
        }
    }
}
