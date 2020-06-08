package com.mini.datastructure.堆.impl;

import com.mini.datastructure.堆.Heap;

/**
 * @author wanghongchao
 * @time 2020/5/28
 */
public class BinaryHeap implements Heap {

    private static final int DEFAULT_CAPACITY = 10;

    private int[] elements;

    private int size;

    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity){
        this.elements = new int[capacity];
    }


    public BinaryHeap(int[] arr){
        this.elements = arr;
        this.size = arr.length;
        heapify();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = 0;
        }
    }

    @Override
    public void add(int element) {
        elements[size] = element;
        siftUp(size);
        size++;
    }

    //大顶堆
    private void siftUp(int index){
        //跟父节点比较
        while (index > 0 && elements[index] > elements[(index -1)/2]){
            int tmp = elements[index];
            elements[index] = elements[(index -1)/2];
            elements[(index -1)/2] = tmp;
            index = (index -1)/2;
        }
    }

    @Override
    public int get() {
        return elements[0];
    }

    @Override
    public void remove() {
        elements[0] = elements[size-1];
        elements[size-1] = 0;
        siftDown(0);
        size--;
    }

    private void siftDown(int index){
        while ((2 * index + 1) < size && elements[2 * index + 1]>elements[index] ||
        (2 * index + 2) < size && elements[2 * index + 2]>elements[index]){

            if(2 * index + 2 < size && (elements[2 * index + 2] > elements[2 * index + 1])){
                int tmp = elements[index];
                elements[index] = elements[2 * index + 2];
                elements[2 * index + 2] = tmp;
                index = 2 * index + 2;
            }else {
                int tmp = elements[index];
                elements[index] = elements[2 * index + 1];
                elements[2 * index + 1] = tmp;
                index = 2 * index + 1;
            }

        }
    }

    @Override
    public int replace(int element) {
        int tmp = elements[0];
        elements[0] = element;
        siftDown(0);
        return tmp;
    }

    @Override
    public void print() {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + "    ");
        }
    }

    private void heapify(){
        for (int i = (size>>>1)-1; i >= 0; i--) {
            siftDown(i);
        }
    }
}
