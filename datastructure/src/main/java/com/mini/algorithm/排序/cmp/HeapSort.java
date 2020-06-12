package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * @author wanghongchao
 * @time 2020/6/11
 */
public class HeapSort {
    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,9,8,2,10};
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    public void heapSort(int[] arr){
        heapify(arr);
        int size = arr.length;
        while (size>1){
            int tmp = arr[0];
            arr[0] = arr[size-1];
            arr[--size] = tmp;
            siftDown(arr,0,size);
        }
    }

    //原地建堆
    private void heapify(int[] arr){
        //自下而上下虑
        int index = (arr.length >> 1) -1;
        for (int i = index;i >= 0;i--){
            siftDown(arr,i,arr.length);
        }
    }
    //下虑
    private void siftDown(int[] arr,int index,int size){
        while (index < size){
            int childIndex = 2 * index + 1;
            if(childIndex >= size) return;
            int rightIndex = childIndex + 1;
            if(rightIndex < size && arr[rightIndex] > arr[childIndex]){
                childIndex = rightIndex;
            }
            if(arr[index] < arr[childIndex]){
                int tmp = arr[index];
                arr[index] = arr[childIndex];
                arr[childIndex] = tmp;
                index = childIndex;
            }else {
                break;
            }
        }
    }
}
