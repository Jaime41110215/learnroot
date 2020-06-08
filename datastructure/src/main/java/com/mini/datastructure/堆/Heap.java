package com.mini.datastructure.堆;

/**
 * @author wanghongchao
 * @time 2020/5/28
 */
public interface Heap {

    int size();

    boolean isEmpty();

    void clear();

    void add(int element);

    int get();

    void remove();

    int replace(int element);

    void print();

}
