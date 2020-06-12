package com.mini.algorithm.排序.cmp;

import org.junit.Test;

/**
 * 插入排序
 * @author wanghongchao
 * @time 2020/6/12
 */
public class InsertionSort {
    @Test
    public void test(){
        int[] arr = {3,4,1,6,7,5,9,8,2,10};
        insertionSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    //basic
    public void insertionSort(int[] arr){
        if(arr==null || arr.length <= 1) return;
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0 && arr[index] < arr[index-1]){
                swap(arr,index-1,index);
                index--;
            }
        }
    }

    //减少交换
    public void insertionSort1(int[] arr){
        if(arr==null || arr.length <= 1) return;
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int val = arr[index];
            while (index > 0 && val < arr[index-1]){
                arr[index] = arr[index-1];
                index--;
            }
            arr[index] = val;
        }
    }

    //二分查找优化
    public void insertionSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            insert(arr,i,binarySearch(arr,i));
        }
    }

    public void insert(int[] arr,int index,int dest){
        int destVal = arr[index];
        for (int i = index; i > dest; i--) {
            arr[i] = arr[i-1];
        }
        arr[dest] = destVal;
    }

    @Test
    public void testb(){
        int[] arr = {1,2,3,4,6,7,9,10}; //target = 5
        //0 9  mid = 4
        //end = 3  mid = 1
        //start = 2 end = 3
        //mid = 2 => start = 3

        int i = binarySearch(arr, 11);
        System.out.println(i);

    }

    //二分查找
    public int binarySearch(int[] arr,int index){
        int begin = 0;
        int end = index;

        while (begin < end){
            int mid = begin + ((end-begin)>>1);
            if(arr[mid] > arr[index]){
                end = mid;
            }else {
                begin = mid + 1;
            }
        }
        return end;
    }

    private void swap(int[] arr,int pre,int post){
        int tmp = arr[pre];
        arr[pre] = arr[post];
        arr[post] = tmp;
    }

}
